package interviewcore.printevenodd;

/**
 * 使用 wait notify 实现交替打印 0 ~10
 * 拿到线程，就打印，打印完，唤醒其他线程，就睡眠
 * 假设线程 A 先拿到锁，打印完之后，唤醒其他线程；自己睡眠，此时 A 释放了 锁，
 * 只有线程 B 可以抢到锁，因为 B 被唤醒了，所以只能 B 来执行了
 * 以此类推
 * 那么怎么实现 3 个数字的交替打印呢？
 * A 拿到锁，打印，唤醒 B ，A 睡，释放锁； C 也是 睡
 * B 拿到锁，打印，唤醒 C，B 睡，释放锁， A 也是 睡
 *
 * 怎么唤醒指定的线程，问题就可以解决了。 notify 本身是唤醒其中一个睡眠的线程，不能指定；要想指定，能够使用的排队的策略
 * A B C 一次排队，A 取出，执行，A 加到对列末尾；A 睡，唤醒 B
 * B 取出，执行，B 加到队列末尾，唤醒 C , 睡
 * BlockingQueue
 *
 * 使用 Condition 完成
 *
 *
 */
public class PrintEvenOddWaitNotify {

    private static int count = 0;
    private static Object lock = new Object();

    static class TunringPrint implements Runnable {

        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ": " + count ++);
                    lock.notify();

                    if (count < 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TunringPrint()).start();
        new Thread(new TunringPrint()).start();
    }


}
