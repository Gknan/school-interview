package concurrenttoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat 1000个任务，
 * 加锁解决线程安全问题
 */
public class ThreadLocalNormalUsage04 {

    public static ExecutorService service =
            Executors.newFixedThreadPool(10);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss");

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    int start = finalI;
                    String date = new ThreadLocalNormalUsage04().date(start);
                    System.out.println(date);
                }
            });
        }
        service.shutdown();
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从 1970.1.1 )):00:)) GMT 计时
        Date date = new Date(1000 * seconds);

        synchronized (ThreadLocalNormalUsage04.class) {
            return dateFormat.format(date);
        }
    }
}
