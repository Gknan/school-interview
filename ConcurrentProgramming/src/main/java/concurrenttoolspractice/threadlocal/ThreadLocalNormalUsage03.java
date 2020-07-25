package concurrenttoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat 1000个任务，打印日期
 * 使用线程池
// * SimpleDateFormat 多次创建被销毁，只创建一次？可行吗
 */
public class ThreadLocalNormalUsage03 {

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
                    String date = new ThreadLocalNormalUsage03().date(start);
                    System.out.println(date);
                }
            });
        }
        service.shutdown();
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从 1970.1.1 )):00:)) GMT 计时
        Date date = new Date(1000 * seconds);

        return dateFormat.format(date);
    }
}
