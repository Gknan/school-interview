package threadpol;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可暂停的线程池
 * 演示每个任务执行的前后都可以放 钩子 函数
 */
public class PausableThreadPool extends ThreadPoolExecutor {

    private boolean isPaused;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();

        try {
            while (isPaused) {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            // 一定要记得释放锁
            lock.unlock();
        }
    }

    private void setPaused() {
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            isPaused = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PausableThreadPool pausableThreadPool = new PausableThreadPool(
                10, 20, 10l, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
        );

        Runnable r = new Runnable() {

            @Override
            public void run() {
                System.out.println("我被执行了");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10000; i++) {
            pausableThreadPool.execute(r);
        }

        Thread.sleep(1500);
        pausableThreadPool.setPaused();
        System.out.println("线程池被暂停了");

        Thread.sleep(3000);
        pausableThreadPool.resume();
        System.out.println("线程池被恢复了");
    }
}
