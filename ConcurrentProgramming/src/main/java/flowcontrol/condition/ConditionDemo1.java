package flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 Condition 的基本用法
 * 绑定在锁上的
 */
public class ConditionDemo1 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    void method1() {
        lock.lock();
        try {
            System.out.println("条件不满足，开始 await");
            condition.await();
            System.out.println("条件满足，执行");
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    void method2() {
        lock.lock();

        try {
            System.out.println("准备工作完成，唤醒其他线程");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ConditionDemo1 demo1 = new ConditionDemo1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(1000);
                    demo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 主线程卡死前，子线程要先启动起来，所以注意这个方法的位置
        demo1.method1();
    }
}
