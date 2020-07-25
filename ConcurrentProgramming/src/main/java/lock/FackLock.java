package lock;

import java.util.concurrent.locks.ReentrantLock;

public class FackLock {

    static ReentrantLock reen = new ReentrantLock();

    public static void main(String[] args) {
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//                testSync();
//            }
//        };

        // interrupt() 修改线程终端状态为 true；如果线程在阻塞状态下，线程将退出阻塞且中断状态将被清除（即为false），且会抛出InterruptException。
        // interrupted() 返回当前中断状态，若重置当前中断状态为 false；
        // isInterrupted() 仅仅返回当前状态
        Thread.currentThread().interrupted();
        System.out.println(Thread.currentThread().isInterrupted()); // 线程
        Thread.currentThread().interrupted();
        System.out.println(Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupted();
        System.out.println(Thread.currentThread().isInterrupted());


        Thread t1 = new Thread() {
            @Override
            public void run() {
                testSync();
            }
        };
        t1.setName("t1....");

        Thread t2 = new Thread() {
            @Override
            public void run() {
                testSync();
            }
        };
        t2.setName("t2....");

        t1.start();
        t2.start();
    }

    public static void testSync() {


        // 加锁实现同步
        reen.lock(); // 加锁的部分是下面的代码块
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        } finally {
            // 释放所
            reen.unlock();
        }


    }
}
