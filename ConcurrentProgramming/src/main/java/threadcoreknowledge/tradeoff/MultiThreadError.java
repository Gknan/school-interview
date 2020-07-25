package threadcoreknowledge.tradeoff;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>第一种：运行结果出错</h1>
 * 演示计数不准确（减少），找出具体出错的位置
 * <p>
 * 思考：怎么捕获到出错的位置
 * 从出错的情况分析，是由于线程1 i++ 的非原子操作导致的，i在执行完 i + 1 后还没有刷新 i 的值，线程2 就开始执行，拿到旧的 i 进行 i++
 * 拆分原子操作，怎么找到位置？
 * <p>
 * 使用一个 volatile 的变量保存最新的index；如在线程1中，开始时 i 为1，经过 i++ 后结果应该是2，所以使用 idx++，i + 1 后，i = i+1 之前，线程 2 获得线程，这时候得到的 index 还是1；
 * idx 表示了线程1 如果是原子性操作，此时的 index 应该的值，如果线程2获取的index 值不等于 idx，那么说明线程1没有执行完就被线程2抢了，这时候线程2的+1就消失了
 */
public class MultiThreadError implements Runnable {

    static MultiThreadError instance = new MultiThreadError();


    int index = 0;

    //    int idx = 0;
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();

    // 让线程在需要等待的地方等待 栅栏，实现对线程流程控制
    static CyclicBarrier cb1 = new CyclicBarrier(2);
    static CyclicBarrier cb2 = new CyclicBarrier(2);

    final boolean[] marked = new boolean[1000000];

    @Override
    public void run() {
//        while (index < 10000) {
//            index ++;
//        }
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
//            if (idx != index) {
//                System.out.println(Thread.currentThread().getName() + "-" + i);
//            }
//
//            synchronized (this) {
//                idx = index;
//                idx++;
//            }

            try {
                // 重置，以便后面使用
                cb2.reset();
                // 等待两个线程都执行了 await 放后放行
                cb1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            index++;
            // 等待两个线程都完成 i ++ 后，再进行数组更新
            // 所以统计期间 index 就不会变化了
            try {
                cb1.reset();
                // 等待两个线程都执行了 await 放后放行
                cb2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            realIndex.incrementAndGet();
            // synchronized 保证了可见性，线程1 执行了之后 index 被更新了
            synchronized (instance) {
                // && marked[index - 1] ??
                // synchronized 可见性；正常情况，0开始，两个线程执行完为2，
                // 这时第一个线程标记marked 位置，第二个线程到这一行判断时，其实也看到的是marked[2] 没有问题，不属于错误
                // 因为正常情况下 marked 数组应该是每隔一位进行一次标记
                if (marked[index] && marked[index - 1]) {
                    System.out.println(Thread.currentThread().getName() + "-" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance, "Thread-01");
        Thread t2 = new Thread(instance, "Thread-02");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
//        System.out.println(instance.index);
        System.out.println("表面是结果：" + instance.index);
        System.out.println("运行次数：" + instance.realIndex);
        System.out.println("错误次数：" + instance.wrongCount);
    }
}
