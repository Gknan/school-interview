package threadcoreknowledge.createthreads;

/**
 * <h1>同时使用 Runnable 和 Thread 两种方式</h1>
 */
public class BothRunnableAndThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自 Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自 Thread");
            }
        }.start();
    }
}
