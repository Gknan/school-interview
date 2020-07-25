package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>证明 wait 只释放当前的那把锁</h1>
 */
public class WaitNorifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("ThreadA got resourceA lock.");
                    synchronized(resourceB) {
                        System.out.println("ThreadA got resourceB lock");
                        try {
                            System.out.println("ThreadA release resourceA lock.");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceA) {
                    System.out.println("ThreadB got resourceA lock.");
                    synchronized(resourceB) {
                        System.out.println("ThreadB got resourceB lock");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

}
