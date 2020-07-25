package threadcoreknowledge.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h1>线程池创建线程的方法</h1>
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService exc = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i ++) {
            exc.submit(new Task());
        };
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch( InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread());
    }
}
