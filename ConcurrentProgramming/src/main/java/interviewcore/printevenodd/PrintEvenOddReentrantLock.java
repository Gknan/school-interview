package interviewcore.printevenodd;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 ReentrantLock 锁控制的方式 实现交替打印 0 ~10
 *
 * count 还是共用一个，声明诚静态变量
 * 使用 Lock 的实现类，逻辑是当
 * 不能靠锁的公平机制来完成
 *
 */
public class PrintEvenOddReentrantLock {

    private static int count = 0;
//    private static Object lock = new Object();
    static ReentrantLock locks = new ReentrantLock(true);

    static CyclicBarrier barrier = new CyclicBarrier(2);

    static class TunringPrint implements Runnable {

        @Override
        public void run() {
            while (count < 100) {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                locks.lock();

                // 锁完就打印
                System.out.println(Thread.currentThread().getName() + ": " + count ++);

                locks.unlock();// 打印完释放锁
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TunringPrint()).start();
        new Thread(new TunringPrint()).start();
    }


}
