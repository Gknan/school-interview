package threadcoreknowledge.stopthreads;

/**
 * <h1>带有 sleep 中断线程的写法</h1>
 */
public class RightWayStopThreadWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是 100 的倍数");
                    }

                    num++;
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(500);
        t.interrupt();

    }
}
