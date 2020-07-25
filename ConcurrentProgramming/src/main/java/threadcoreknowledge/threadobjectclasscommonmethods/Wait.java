package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * <h1>展示 wait 和 notify 的基本用法</h1>
 * 1，研究代码执行顺序
 * 2，证明 wait 释放锁
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() +" 执行了");
                try {
                    object.wait(); // 进入 wait 状态并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程" + Thread.currentThread().getName() +"获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程"
                        + Thread.currentThread().getName()
                        +" 调用了 notify");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
