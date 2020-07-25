package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：模拟 100 米跑步，5名选手等裁判员发令
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);// 参数表示的是有几次倒数

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令。");
                    try {
//                        begin.wait();
                        begin.await();// 总之就是，调用 CountDownLatch#await 方法，当前线程会判断此时 state 是不是 0，是0直接运行，不是则阻塞
                        System.out.println("No." + no + "开始跑了。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        // 裁判员检车发令枪
        Thread.sleep(5000);
        System.out.println("发令抢响，比赛开始");
        begin.countDown();
    }
}
