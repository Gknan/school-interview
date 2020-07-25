package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>两个交替打印 0 ~ 100，用 synchronize 关键字实现</h1>
 * 1，新建 2 个线程；一个只处理偶数，一个只处理奇数 （位运算）
 * 2，用 synchronize 来通信
 *
 * 存在问题：效率低，存在空转
 */
public class WaitNotifyPrintAllEvenOddSync {

    private static int count;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (count < 100) {
                    synchronized(lock) {
                        if (count < 100) {
                            if ((count & 1) == 0) {
                                System.out.println(Thread.currentThread().getName()
                                        + ": " + count++);
                            }
                        }
                    }
                }
            }
        }, "偶数").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (count < 100) {
                    synchronized(lock) {
                        if (count < 100) {
                            if ((count & 1) != 0) {
                                System.out.println(Thread.currentThread().getName()
                                        + ": " + count++);
                            }
                        }
                    }
                }
            }
        }, "奇数").start();
    }
}
