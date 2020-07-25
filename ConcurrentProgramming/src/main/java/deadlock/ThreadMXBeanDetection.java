package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * <h1>用 ThreadMXBean 检死锁</h1>
 */
public class ThreadMXBeanDetection implements Runnable {

    int flag = 1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    @Override
    public void run() {
        System.out.println(flag);

        if (flag == 1) {
            synchronized(o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o2) {
                    System.out.println("线程1拿到两把锁");
                }
            }
        } else {
            synchronized(o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o1) {
                    System.out.println("线程2拿到两把锁");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection r1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection r2 = new ThreadMXBeanDetection();
        r1.flag = 1;
        r2.flag = 0;

        new Thread(r1).start();
        new Thread(r2).start();

        // 保证已经给发生了死锁，在执行检测的代码
        Thread.sleep(1000);

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadLockedThreads = threadMXBean.findDeadlockedThreads();

        if (deadLockedThreads != null && deadLockedThreads.length > 0) {
            for (long id: deadLockedThreads) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(id);
                System.out.println("发现死锁：" + threadInfo.getThreadName());
                // TODO 报警 日志记录等
            }
        }
    }
}
