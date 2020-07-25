package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：模拟 100 米跑步，5名选手等裁判员发令
 * 终点裁判等待最后一个运动员宣布比赛结束
 */
public class CountDownLatchDemo3 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);// 参数表示的是有几次倒数

        CountDownLatch end =  new CountDownLatch(5);// 终点裁判员需要倒计时的个数为 5 ，5 个运动员

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令。");
                    try {
//                        begin.wait();
                        begin.await();
                        System.out.println("No." + no + "开始跑了。");
                        Thread.sleep((long)(Math.random() * 10000));
//                        System.out.println("No." + no + "到达终点");
//                        end.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("No." + no + "到达终点");
                        end.countDown();
                    }
                }
            };
            executorService.execute(runnable);
        }

//        // 终点裁判员等待 运动员跑到终点
//        end.await();
        // 裁判员检车发令枪
        Thread.sleep(5000);
        System.out.println("发令抢响，比赛开始");
        begin.countDown();

        // 终点裁判员等待 运动员跑到终点
        end.await();

        System.out.println("最后一个运动员到达，比赛结束。");
    }
}
