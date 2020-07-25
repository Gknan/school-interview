package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>volatile 适用的情况1</h1>
 */
public class UseVolatile implements Runnable {
    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new UseVolatile();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(((UseVolatile) r).done);
        System.out.println(((UseVolatile) r).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }
}
