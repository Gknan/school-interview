package interviewcore.consumerproducermode;

import java.util.LinkedList;
import java.util.Random;

/**
 * 生产者消费者实现方式1  wait notify
 */
public class ConsumerProducerWaitNotify {

    public static LinkedList<Integer> storage = new LinkedList<>();
    public static  int maxSize = 10;


    public static void main(String[] args) {
        Producer producer = new Producer(storage, maxSize);
        Consumer consumer = new Consumer(storage, maxSize);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {

        private LinkedList<Integer> storage;
        private int maxSize;

        public Producer(LinkedList<Integer> storage, int maxSize) {
            this.storage = storage;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (storage) {
                    while (storage.size() == maxSize) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 生产
                    storage.offer(new Random().nextInt());
                    //
                    System.out.println("生产者生茶了一个对象");
                    storage.notify();
                }
            }
        }
    }


    static class Consumer implements Runnable {
        private LinkedList<Integer> storage;
        private int maxSize;

        public Consumer(LinkedList<Integer> storage, int maxSize) {
            this.storage = storage;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (storage) {
                    while (storage.isEmpty()) {
                        try {
                            storage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    storage.poll();
                    System.out.println("消费者消费了一个数据....");
                    // 唤醒
                    storage.notify();
                }
            }
        }
    }
}
