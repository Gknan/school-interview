package concurrenttoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat 两个线程使用
 */
public class ThreadLocalNormalUsage {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage().date(1007);
                System.out.println(date);
            }
        }).start();
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从 1970.1.1 )):00:)) GMT 计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss");
        return dateFormat.format(date);
    }
}
