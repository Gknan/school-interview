package threadcoreknowledge.createthreads.wrongways;

/**
 * <h1>lambda 表达式创建</h1>
 */
public class LamdaDemo {

    public static void main (String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
