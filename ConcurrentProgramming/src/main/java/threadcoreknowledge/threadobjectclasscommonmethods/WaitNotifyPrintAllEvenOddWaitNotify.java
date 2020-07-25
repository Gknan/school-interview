package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>两个交替打印 0 ~ 100，用 wait/notify 实现</h1>
 * 1，拿到锁，就打印
 * 2，打印完，唤醒其他线程，就休眠
 *
 * 存在问题：效率低，存在空转
 */
public class WaitNotifyPrintAllEvenOddWaitNotify {
    private static int count = 0;

    private static final Object lock = new Object();

    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count < 100) {
                synchronized(lock) {
                    System.out.println(Thread.currentThread().getName() + ": " + count++);

                    lock.notify();
                    // 如果任务还没有结束，让出线程
                    if (count < 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }



    public static void main(String[] args) {
        new Thread(new TurningRunner(), "偶数").start();
        new Thread(new TurningRunner(), "奇数").start();
    }
}
