package threadpol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CatchedThreadPool
 * 没有队列，来任务就创建线程
 */
public class CatchedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task2());
        }

    }
}
