package threadcoreknowledge.startthread;

/**
 * <h1>不能两次调用 start，否则会报错</h1>
 * 抛出 java.lang.IllegalThreadStateException
 */
public class CanStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();

        thread.start();
        thread.start();
    }
}
