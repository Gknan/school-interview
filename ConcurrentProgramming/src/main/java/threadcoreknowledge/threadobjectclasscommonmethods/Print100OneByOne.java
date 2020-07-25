package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>俩个线程交替打印 0 ~ 100 的奇偶数</h1>
 *
 * synchronized () 中调用 wait notify 方法时要使用 lock.wait lock.notify 来调用
 */
public class Print100OneByOne {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ThreadA r = new ThreadA(o);
        ThreadB r2 = new ThreadB(o);

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r2);
        t1.start();

        Thread.sleep(10);
        t2.start();
    }
}

class ThreadA implements Runnable {
//    private volatile Integer resource;
    private static Object resource;


    public ThreadA(Object resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        synchronized(resource) {
            int i = 0;
            while (i < 100) {

//                notify();
                resource.notify();
                System.out.println(i);
//                resource ++;

                i += 2;

                // 睡眠需要判断，否则，这种有线程将会一直睡眠
                if (i < 100) {
                    try {
//                    wait();
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class ThreadB implements Runnable {
    //    private volatile Integer resource;
    private static Object resource;


    public ThreadB(Object resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        synchronized (resource) {
            int i = 1;
            while (i < 100) {

//                notify();
                resource.notify();
                System.out.println(i);
//                resource ++;

                i += 2;

                if (i < 100) {
                    try {
//                    wait();
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}