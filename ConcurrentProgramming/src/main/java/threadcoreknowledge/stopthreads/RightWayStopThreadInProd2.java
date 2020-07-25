package threadcoreknowledge.stopthreads;

/**
 * <h1>在 catch 语句中调用 Thread.currentThread().interrupt() 来
 * 恢复设置中断状态，方便后续执行中，依然能够检查到刚才放生了中断
 * </h1>
 *
 */
public class RightWayStopThreadInProd2 implements Runnable{
    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadInProd2());
        t.start();

        Thread.sleep(1000);
        t.interrupt();
    }
}
