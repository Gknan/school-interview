package threadcoreknowledge.sixstates;

/**
 * <h1>展示线程的 NEW RUNNABLE TERMINATED 状态</h1>
 * 即使是正在运行，也是 RUNNABLE 状态，而不是 RUNNING
 */
public class NewRunnableTerminated implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i ++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());

        System.out.println(thread.getState());

        thread.start();

        System.out.println(thread.getState());

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 即使是正在运行，也是 RUNNABLE
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState());
    }
}
