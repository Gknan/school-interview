package threadcoreknowledge.createthreads;

/**
 * <h1>用 Runnable 方法创建线程</h1>
 * 实现 Runnable 接口，重写 run 方法
 */
public class RunnableStyle implements Runnable {
    @Override
    public void run() {
        System.out.println("用 Runable 方法创建线程");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new RunnableStyle());

        t.start();
    }
}
