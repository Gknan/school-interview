package interviewcore.printevenodd;

/**
 * 0~100 交替打印 wait notify
 */
public class PrintABwaitnotify {

    // 共享的部分
    public static int num = 1;
    public static Object lock = new Object();

    public static void main(String[] args) {
        Turing odd = new Turing();
        Turing even = new Turing();

        Thread t1 = new Thread(odd, "奇数");
        Thread t2 = new Thread(even, "偶数");

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }

    static class Turing implements Runnable{
//        private int flag = 0;
        @Override
        public void run() {
            while (num < 101) {
//                if (flag== 0) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + ": " + num);
                        num++;
                        lock.notify();
                        if (num < 101) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                } else if (flag == 1) {
//                    synchronized (lock) {
//                        System.out.println(Thread.currentThread().getName() + ": " + num);
//                        num ++;
//                        lock.notify();
//                        if (num < 100) {
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}
