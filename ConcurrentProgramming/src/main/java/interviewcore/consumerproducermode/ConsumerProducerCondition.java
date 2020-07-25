package interviewcore.consumerproducermode;

import flowcontrol.condition.ConditionDemo1;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Condition 条件队列完成 生产者消费者
 */
public class ConsumerProducerCondition {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition notFull = lock.newCondition();
    public static Condition notEmpty = lock.newCondition();

    public static LinkedList<Integer> storage = new LinkedList<>();
    public static int maxSize = 10;

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();

        consumer.start();
        producer.start();
    }


    static class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();

                try {
                    while (storage.isEmpty()) {
                        notEmpty.await();
                    }

                    //消费
                    Integer poll = storage.poll();
                    System.out.println("消费者消费了：" + poll);
                    // 通知生产者
                    notFull.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();

                try {
                    while (storage.size() == maxSize) {
                        notFull.await();
                    }

                    // 生产
                    storage.add(new Random().nextInt());
                    System.out.println("生产者生产了一个数据");

                    // 通知消费者
                    notEmpty.signal();
                } catch (InterruptedException e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
