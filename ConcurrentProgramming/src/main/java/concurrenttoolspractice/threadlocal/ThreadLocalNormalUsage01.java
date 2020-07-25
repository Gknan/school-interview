package concurrenttoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat 10个线程使用，打印日期
 */
public class ThreadLocalNormalUsage01 {

    public static void main(String[] args) {

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int start = 1000 + finalI * 1023;
                    String date = new ThreadLocalNormalUsage01().date(start);
                    System.out.println(date);
                }
            }).start();
        }
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从 1970.1.1 )):00:)) GMT 计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss");
        return dateFormat.format(date);
    }
}
