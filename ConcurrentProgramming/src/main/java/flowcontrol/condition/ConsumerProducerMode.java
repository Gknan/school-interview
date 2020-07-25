package flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式使用 Condition 实现
 */
public class ConsumerProducerMode {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConsumerProducerMode mode = new ConsumerProducerMode();
        Producer producer = mode.new Producer();
        Consumer consumer = mode.new Consumer();
        producer.start();
        consumer.start();
    }


    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            // 不挺的生产
            while (true) {
                lock.lock();

                try {
                    // 生产前判断对垒是否为慢
                    while(queue.size() == queueSize) {
                        System.out.println("队列满，等待消费");
                        // 到非满队列等待唤醒
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 队列不满，可以生产一个
                    queue.add((int)(Math.random() * 1000));
                    System.out.println("生产成功，当前队列剩余空间为：" + (queueSize - queue.size()));
                    // 此时队列一定不空，唤醒非空队列中的线程去消费
                    notEmpty.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) { // 不停的消费
                lock.lock();

                try {
                    while (queue.size() == 0) {
                        System.out.println("队列空，等待生产");
                        // 消费前，看队列是否为空
                        // weikong ,等待
                        try {
                            notEmpty.await();// 等待条件 不空 不空的时候我才运行，所以先去不空的队列等待，等待不空时有人唤醒我
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.poll();
                    // 我消费了，队列肯定是不满的，唤醒不满队列中的生产者去生产
                    notFull.signalAll();
                    System.out.println("从队列里取走了一个数据，" +
                            "队列中还" + queue.size() +
                            "有个元素");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
