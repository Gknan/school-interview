package threadcoreknowledge.uncaughtexception;

/**
 * <h1>单线程，抛出，打印异常堆栈，多线程，子线程发现异常，会怎样</h1>
 */
public class ExceptionInChildThread implements Runnable {

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();

        for (int i = 0; i < 1000; i ++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
