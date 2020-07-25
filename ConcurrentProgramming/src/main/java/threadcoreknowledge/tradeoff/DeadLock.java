package threadcoreknowledge.tradeoff;

/**
 * <h1>第二种线程安全问题，演示死锁</h1>
 */
public class DeadLock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
//        MyRunnable1 r1 = new MyRunnable1();
        Thread t1 = new Thread(new MyRunnable1());
        Thread t2 = new Thread(new MyRunnable2());

        t1.start();
        t2.start();
    }


    static class MyRunnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("线程1获取到锁1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1尝试获取到锁2");
                synchronized (lock2) {

                }
            }
        }
    }

    static class MyRunnable2 implements Runnable {

        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("线程2获取到锁2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2尝试获取到锁1");
                synchronized (lock1) {

                }
            }
        }
    }
}

