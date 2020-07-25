package threadcoreknowledge.uncaughtexception;

/**
 * 使用自定义的异常处理器捕获异常
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler("捕获器1"));
        // try catch 是针对主线程的，而抛出异常是在子线程中
        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread1").start();
        Thread.sleep(300);

        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread2").start();
        Thread.sleep(300);

        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread3").start();
        Thread.sleep(300);

        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread4").start();
        Thread.sleep(300);

    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
