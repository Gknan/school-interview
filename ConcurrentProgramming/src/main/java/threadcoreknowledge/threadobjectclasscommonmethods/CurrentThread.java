package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>演示打印 main thread0 thread1</h1>
 */
public class CurrentThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new CurrentThread().run();

        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }
}
