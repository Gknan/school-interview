package interviewcore.printevenodd;

import java.util.concurrent.TimeUnit;

/**
 * 取巧的方案：每次打印前判断，满足条件才打印
 */
public class PrintABWithJudge {


    public static Object lock = new Object(); // lock 也是公共资源

    public static int num = 1; // num 应该作为两个线程的公共资源

    public static void main(String[] args) {
        Tunring odd = new Tunring();
        Tunring even = new Tunring();
        odd.flag = 1;
        even.flag = 0;

        new Thread(odd, "奇数").start();
        new Thread(even, "偶数").start();
    }

    static class Tunring implements Runnable {

        private int flag = 0;
        @Override
        public void run() {
            while (num < 100) {
                if (flag == 0) {
                    synchronized (lock) {
                        if (num % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + "：" + num);
                            num ++;
                        }
                    }
                } else if (flag == 1) {
                    synchronized (lock) {
                        if (num % 2 != 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + num);
                            num ++;
                        }
                    }
                }
            }
        }
    }

}
