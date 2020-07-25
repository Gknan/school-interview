package concurrenttoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat 1000个任务，打印日期
 * 利用 ThreadLocal 个每个线程分配自己的 DateFormat 对象，保证了线程安全，效率高
 *
 * 创建的 SimpleDateFormat 是在线程里面的，对于线程池而言，核心线程是固定的但是每个线程的任务是不停变换执行的
 *
 */
public class ThreadLocalNormalUsage05 {

    public static ExecutorService service =
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    int start = finalI;
                    String date = new ThreadLocalNormalUsage05().date(start);
                    System.out.println(date);
                }
            });
        }
        service.shutdown();
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从 1970.1.1 )):00:)) GMT 计时
        Date date = new Date(1000 * seconds);
//        SimpleDateFormat dateFormat =
//                new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss");
        SimpleDateFormat dateFormat =
                ThreadSafeFormatter.dateFormatThreadLocal2.get();
        return dateFormat.format(date);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal
            = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss");
        }
    };

    // lambada 表达式的写法
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 =
            ThreadLocal.withInitial( () ->
                    new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss")
            );
}