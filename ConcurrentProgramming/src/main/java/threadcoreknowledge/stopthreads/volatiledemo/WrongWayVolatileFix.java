package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <h1>用中断修复阻塞导致的无尽等待</h1>
 */
public class WrongWayVolatileFix {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        WrongWayVolatileFix body = new WrongWayVolatileFix();

        Producer producer = body.new Producer(storage);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = body.new Consumer(storage);
        while (consumer.needMoreNums()) {
            try {
                System.out.println(consumer.storage.take() + "被消费了");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者不需要消费了");

        // 一旦消费者不需要更多数据了，我们应该让生产者也停下来，
        producerThread.interrupt();
//        producer.canceled = true;
//        System.out.println(producer.canceled);
    }

    class Producer implements Runnable {

//        public volatile boolean canceled = false;

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + " 是 100 的倍数，放到仓库中。 ");
                        storage.put(num);
                    }
                    num++;
//                Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者停止");
            }
        }
    }

    class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }

            return true;
        }
    }

}
