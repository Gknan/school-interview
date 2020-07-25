package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <h1>展示线程 sleep 时不释放 lock</h1>
 *
 */
public class SleepDontReleaseLock implements Runnable {
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        syn();
    }

    private void syn() {
        lock.lock();
        System.out.println("线程 " + Thread.currentThread().getName() +
                " 获取到了锁");

        try {
            Thread.sleep(5000);
            System.out.println("线程 " + Thread.currentThread().getName() +
                    " 已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 使用 锁一定要手动释放
            lock.unlock();
        }

        System.out.println("线程 " + Thread.currentThread().getName() +
                " 退出了同步代码块");
    }

    public static void main(String[] args) {
        SleepDontReleaseLock monitor = new SleepDontReleaseLock();
        new Thread(monitor).start();
        new Thread(monitor).start();
    }
}
