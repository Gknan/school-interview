package interviewcore.consumerproducermode;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用 BlockingQueue 实现生产者消费者模式
 */
public class ConsumerProcuderBlockingQueue {

    public static LinkedBlockingQueue<Integer> storage = new LinkedBlockingQueue<>(10);
//    public static int maxSize = 10;

    public static void main(String[] args) {
        Consumer consumer = new Consumer(storage, 10);
        Producer producer = new Producer(storage, 10);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Consumer implements Runnable {

        private LinkedBlockingQueue<Integer> storage;
        private int maxSize;

        public Consumer(LinkedBlockingQueue<Integer> storage, int maxSize) {
            this.storage = storage;
            this.maxSize = maxSize;
        }

        public void consume() {
            while (true) {
                try {
                    Integer take = storage.take();
                    System.out.println("Consumer拿到了：" + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            consume();
        }
    }

    static class Producer implements Runnable {
        private LinkedBlockingQueue<Integer> storage;
        private int maxSize;

        public Producer(LinkedBlockingQueue<Integer> storage, int maxSize) {
            this.storage = storage;
            this.maxSize = maxSize;
        }

        public void produce() {
            while (true) {
                try {
                    storage.put(new Random().nextInt());
                    System.out.println("生产者生者了一个数据");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void run() {
            produce();
        }
    }
}
