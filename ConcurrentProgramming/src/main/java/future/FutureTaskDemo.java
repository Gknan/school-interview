package future;

import javafx.concurrent.Task;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示 FutureTask 的用法
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);
        executorService.submit(futureTask);
//        Thread t = new Thread(futureTask);
//        t.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {

        }
    }


    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");

            Thread.sleep(2000);

            return new Random().nextInt();
        }
    }
}
