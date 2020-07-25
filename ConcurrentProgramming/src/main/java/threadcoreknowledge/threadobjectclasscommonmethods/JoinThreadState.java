package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>先 join 再 getState</h1>
 * 或者 通过 debugger 看线程 join 前后状态的对比
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(mainThread.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() +
                        " 执行完毕");
            }
        });
        t1.start();

        t1.join();
    }

}
