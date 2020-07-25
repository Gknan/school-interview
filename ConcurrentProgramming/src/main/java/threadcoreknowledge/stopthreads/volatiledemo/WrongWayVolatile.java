package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * <h1>演示用 volatile 的局限：part1 看似可行</h1>
 */
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + " 是 100 的倍数 ");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WrongWayVolatile w = new WrongWayVolatile();
        Thread t = new Thread(w);

        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        w.canceled = true;
    }
}
