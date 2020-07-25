package flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 演示  Semaphore 用法
 */
public class SemaphoreDemo1 {

    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 100; i++) {
            service.execute(new Task());
        }

        service.shutdown();
    }


    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证。");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 拿到了3个，就要释放3个，拿到多少释放多少。
                semaphore.release(3);
                System.out.println(Thread.currentThread().getName() + "释放了许可证。");
            }
        }
    }
}
