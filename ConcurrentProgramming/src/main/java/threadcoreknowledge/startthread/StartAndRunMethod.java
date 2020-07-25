package threadcoreknowledge.startthread;

/**
 * <h1>对比 start 和 run 两种启动线程的方式</h1>
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        runnable.run();

        new Thread(runnable).start();
//        new Thread(runnable).run();
    }
}
