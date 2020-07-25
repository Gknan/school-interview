package threadcoreknowledge.stopthreads;

/**
 * <h1>如果在执行过程中，每次循环都会 sleep 或 wait 等，那么不需要每次迭代都检查是否中断</h1>
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;

            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是 100 的倍数");
                    }

                    num++;
                    Thread.sleep(10);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(5000);
        t.interrupt();
    }
}
