package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>演示 join 期间被中断的效果</h1>
 */
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("子线程中断");
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() +
                        " 执行完毕");
            }
        });

        t1.start();
        System.out.println("等待子线程执行完毕");
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() +
                    "中断了");
            e.printStackTrace();
            t1.interrupt();
        }

        System.out.println("子线程运行完毕");
    }
}
