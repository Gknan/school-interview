package aqs;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * <h1>自己用 aqs 实现简单的协作类</h1>
 *
 * 协作类的功能，传入参数 k，表示 等待 k 个线程到齐，一起执行
 * 开始 c = k；
 * 每个线程来调用 await 方法时，更新 c；若 c > 0；线程封装，加到 AQS 队列中
 * 否则，c == 0，唤醒队列中的所有线程
 * 1. 梳理逻辑，await 方法中， 释放
 * 2，继承
 * 3，共享还是独占，共享
 */
public class OneShotLatch {

    private final Sync sync = new Sync();


    private class Sync extends AbstractQueuedLongSynchronizer {
        @Override
        protected long tryAcquireShared(long arg) {
            return getState() == 1 ? 1 : -1;
//            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(long arg) {
            setState(1);


            return true;
//            return super.tryReleaseShared(arg);
        }
    }

    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(1);
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +
                            "尝试获取 latch，获取失败就等待");
                    oneShotLatch.await();
                    System.out.println(Thread.currentThread().getName() + "继续执行");
                }
            }).start();
        }

        Thread.sleep(5000);
        oneShotLatch.signal();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +
                        "尝试获取 latch，获取失败就等待");
                oneShotLatch.await();
                System.out.println(Thread.currentThread().getName() + "继续执行");
            }
        }).start();


    }
}
