package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>join 的等价形式</h1>
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() +
                        " 执行完毕");
            }
        });
        t1.start();
        System.out.println("开始等待子线程运行完毕");
//        t1.join();
        // 等价代码
        synchronized(t1) {
            t1.wait();
        }
        System.out.println("所有子线程运行完毕");
    }
}
