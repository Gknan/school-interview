package threadcoreknowledge.createthreads;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableStyle implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("hhhhhhhhhhhhhhh");
        return "hhhh";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(new CallableStyle());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
