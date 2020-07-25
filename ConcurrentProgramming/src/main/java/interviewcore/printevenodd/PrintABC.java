package interviewcore.printevenodd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {

    private static int count = 0;
//
    static ReentrantLock lock = new ReentrantLock();
//    static Condition condition1 = lock.newCondition();
//    static Condition condition2 = lock.newCondition();
//    static Condition condition3 = lock.newCondition();

    public static void main(String[] args) {

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        Print printA = new Print(condition1, condition2);
        Print printB = new Print(condition2, condition3);
        Print printC = new Print(condition3, condition1);

        new Thread(printA).start();
        new Thread(printB).start();
        new Thread(printC).start();
    }

    static class Print implements Runnable {

        Condition condition1;
        Condition condition2;

        public Print(Condition condition1, Condition condition2) {
            this.condition1 = condition1;
            this.condition2 = condition2;
        }

        @Override
        public void run() {
            while (count < 100) {
                // 拿到锁
                lock.lock(); // 使用 lock 一定要释放锁资源
                System.out.println(count ++);

                // 唤醒下一个线程
                condition2.signal();

                // 睡眠自己

                try {
                    if (count < 100) {
                        condition1.await();
                    } else {
                        // 唤醒其他队列中阻塞的线程
//                    condition1.signal();
                        condition2.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }

            }
        }


    }

}
