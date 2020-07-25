package threadcoreknowledge.uncaughtexception;

/**
 * <h1></h1>
 * 1，不加 try/catch 抛出 4 个异常
 * 2，加 try  catch ，期望捕获第一个线程的异常，线程 234 不应该运行，希望看到 Caught Exception
 * try catch 针对主线程而言来捕获，比如 start 方法启动时抛出异常，可以捕获；但是我们的异常在子线
 * 程中抛出，所以捕获不到
 * 3，执行时发现，没有 Caught Exception，线程234 依然运行，并且抛出异常
 *
 * 说明线程异常不能用传统方法捕获
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        // try catch 是针对主线程的，而抛出异常是在子线程中
        try {
            new Thread(new CantCatchDirectly(), "myThread1").start();
            Thread.sleep(300);

            new Thread(new CantCatchDirectly(), "myThread2").start();
            Thread.sleep(300);

            new Thread(new CantCatchDirectly(), "myThread3").start();
            Thread.sleep(300);

            new Thread(new CantCatchDirectly(), "myThread4").start();
            Thread.sleep(300);
        } catch (RuntimeException r) {
            System.out.println("Caught Exception");
        }

    }

    @Override
    public void run() {
//        try {
          throw new RuntimeException();
//        } catch (RuntimeException r) {
//            // 修复逻辑，报警机制
//            System.out.println("Caught Exception");
//        }
//
//        for (int i = 0; i < 10; i ++) {
//            System.out.println("jjjjjjj");
//        }
    }
}
