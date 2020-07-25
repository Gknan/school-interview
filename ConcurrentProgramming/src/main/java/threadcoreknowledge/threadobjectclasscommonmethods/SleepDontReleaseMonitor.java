package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>展示线程 sleep 时不释放 synchronize 的 monitor</h1>
 * 等 sleep 时间到了以后，正常结束后才释放锁
 */
public class SleepDontReleaseMonitor implements Runnable {
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("线程" + Thread.currentThread().getName() +
                " 获取到了 monitor");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程" + Thread.currentThread().getName() +
                " 退出了同步代码块");
    }

    public static void main(String[] args) {
        SleepDontReleaseMonitor monitor = new SleepDontReleaseMonitor();
        new Thread(monitor).start();
        new Thread(monitor).start();
    }
}
