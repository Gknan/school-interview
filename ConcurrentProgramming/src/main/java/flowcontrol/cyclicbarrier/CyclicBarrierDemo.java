package flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示 CyclicBarrier
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        // 凑齐 5 个触发一次
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                // 定义所有人到了集结点之后的操作
                System.out.println("所有人到期了，一起出发");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i, cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable {

        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "现在前往集合的地点");
            try {
                Thread.sleep((long)(Math.random() * 10000));
                System.out.println("线程" + id + "到了集合地点，开始等待其他人到达。");

                // 每个线程执行完之后需要 await
                cyclicBarrier.await();
//                System.out.println("线程" + id + "触发了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
