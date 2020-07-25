package threadpol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool 会导致 OOM
 *
 * 设置 堆区的大小 -Xms8m   -Xmx8m
 */
public class FixedThreadPoolOOM {

    private static ExecutorService executorService =
            Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
            
        }
    }
}

class SubThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
