package threadpol;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 关闭线程池方法1
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTast());
        }

        Thread.sleep(1500);

//        System.out.println(executorService.awaitTermination(3, TimeUnit.SECONDS));
//        System.out.println(executorService.isShutdown());
//
//        executorService.shutdown();
//
//        // 停止命令后返回 true
//        System.out.println(executorService.isShutdown());
//
//        // 线程池中所有任务已完成
//        System.out.println(executorService.isTerminated());
//        /// 提交新任务会报错
//        executorService.execute(new ShutDownTast());
//
//        Thread.sleep(10000);

        // 演示 shutdownNow
        List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables);
    }
}


class ShutDownTast implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500L);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
            e.printStackTrace();
        }

    }
}