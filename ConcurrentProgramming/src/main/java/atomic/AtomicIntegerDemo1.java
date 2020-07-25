package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对比非原子类的线程安全问题，使用了原子类之后，不需要加锁
 */
public class AtomicIntegerDemo1 implements Runnable {

    private static final AtomicInteger integer = new AtomicInteger();

    public void incrementAtomic() {
//        integer.getAndIncrement();
//        integer.getAndDecrement();
        integer.getAndAdd(-2);
    }

    private static volatile int basicCount = 0;

    public void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(integer);
        System.out.println(basicCount);
    }
}
