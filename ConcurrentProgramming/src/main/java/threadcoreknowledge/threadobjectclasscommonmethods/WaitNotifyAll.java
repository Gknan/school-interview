package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1> 3 个线程，线程1 2 首先被阻塞，线程 2 唤醒它们。notify notifyAll</h1>
 * start 先执行不代表线程先启动
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized(resourceA) {
            System.out.println(Thread.currentThread().getName() + "" +
                    " got resourceA lock.");

            try {
                System.out.println(Thread.currentThread().getName() + "" +
                        " waits to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "" +
                        " is waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized(resourceA) {
                    resourceA.notifyAll();
//                    resourceA.notify();
                    System.out.println(Thread.currentThread().getName() + "" +
                            " Thread t3 notified.");
                }
            }
        });

        t1.start();
        t2.start();

//        Thread.sleep(200);
        t3.start();

    }
}
