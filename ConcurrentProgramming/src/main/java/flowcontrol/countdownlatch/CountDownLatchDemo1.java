package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：工厂中，质检，5个工人检查，所有人都认为通过，才通过
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    try {
                        // 检测
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("No. " + no + "完成了检查。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 完成一个事件倒计时一次
                        latch.countDown();
                    }
                }
            };
            executorService.execute(runnable);
        }

        System.out.println("等待 5 个人检查完....");
        // 主线程去执行，若 门闩值不是 0，陷入等待啊啊
        latch.await();// 谁等待谁调用 await 方法
        System.out.println("所有人都完成了工作，进入下一个环节");
    }
}
