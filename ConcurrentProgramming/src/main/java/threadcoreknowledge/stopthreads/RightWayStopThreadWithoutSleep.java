package threadcoreknowledge.stopthreads;

/**
 * <h1>run 方法中没有 sleep 或者 wait 方法</h1>
 */
public class RightWayStopThreadWithoutSleep
implements Runnable{


    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted()
                && num < Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + " 是 10000 的倍数");
            }

            num ++;
        }

        System.out.println("执行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadWithoutSleep());
        t.start();

        Thread.sleep(2000);
        t.interrupt();
    }
}
