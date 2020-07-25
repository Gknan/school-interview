package threadpol;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class CuscomerThreadPool {

    public static int cpuCore;

    public static void main(String[] args) {

        // 核数
        cpuCore = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuCore);


        // CPU 密集型 线程池核心数为 core+1
        // IO 密集型 线程池核心数为 core * 2


        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 9; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "执行");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
