package threadcoreknowledge.sixstates;

/**
 * <h1>展示 Blocked Waiting Timed_Waiting</h1>
 */
public class BlockedWaitingTimedWaiting implements Runnable {


    @Override
    public void run() {
        sync();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(10);
        // 线程1 正在执行Thread.sleep(1000); 打印出 TIMED_WAITING
        System.out.println(t1.getState());
        // 线程2 打印出 BLOCKED ，因为 进入 synchronize 代码块没有获得锁在等待
        System.out.println(t2.getState());

        Thread.sleep(1300);

        // 预测打印 WAITING，因为调用了 wait() 方法
        System.out.println(t1.getState());
    }

    private synchronized void sync() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
