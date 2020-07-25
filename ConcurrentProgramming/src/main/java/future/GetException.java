package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * get 方法过程中抛出异常
 * for 循环演示抛出 Exception 的时机；并不是易产生就抛出，直到调用 get 方法抛出
 */
public class GetException {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Future<Integer> submit = executorService.submit(new CallableTask());

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            // 任务完成 或异常终止都会 true
            System.out.println(submit.isDone());
            // 只有 get 时才能看到 异常信息
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException");
        } finally {
            executorService.shutdown();
        }
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable 抛出异常");
        }
    }
}
