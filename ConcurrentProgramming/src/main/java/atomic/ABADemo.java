package atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA 问题的模拟和解决
 */
public class ABADemo {

    public static void main(String[] args) {
//        AtomicInteger money = new AtomicInteger(100);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 模拟ABA
//                System.out.println(Thread.currentThread().getName() + " 开始修改");
//                money.compareAndSet(100, 101);
//                money.compareAndSet(101, 100);
//            }
//        }, "t1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " 开始修改");
//                try {
//                    // 等待线程1执行完毕
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                boolean flag = money.compareAndSet(100, 109);
//                System.out.println(Thread.currentThread().getName() + "修改成功？" + flag);
//            }
//        }, "t2").start();

        System.out.println("===================下面解决ABA=================");

        // 添加一个版本号来解决 ABA 问题

        AtomicStampedReference<Integer> money2 = new AtomicStampedReference<>(100, 1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始");
                System.out.println("ABA 之前 Stamp 是" + money2.getStamp());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                money2.compareAndSet(100, 101, money2.getStamp(), money2.getStamp() + 1);
                money2.compareAndSet(101, 100, money2.getStamp(), money2.getStamp() + 1);

                System.out.println("ABA 之后 Stamp 是" + money2.getStamp());
            }
        }, "t3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = money2.getStamp();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始 stamp 是 " + stamp);
                boolean flag = money2.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName() + "修成成功？" + flag);
            }
        }, "t4").start();

    }
}
