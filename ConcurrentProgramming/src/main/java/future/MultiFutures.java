package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 批量提交任务，用 List 批量接收结果
 */
public class MultiFutures {

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(20);

        ArrayList<Future> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = executorService.submit(new CallableTask());
            list.add(future);
        }

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = list.get(i);
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
