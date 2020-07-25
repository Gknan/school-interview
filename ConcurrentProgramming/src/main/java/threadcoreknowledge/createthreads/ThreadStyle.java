package threadcoreknowledge.createthreads;

/**
 * <h1>用 Thread 方法实现线程</h1>
 * 继承 Thread 类，重写 run 方法
 */
public class ThreadStyle extends Thread {

    public static void main(String[] args) {
        Thread t2 = new ThreadStyle();

        t2.start();
    }

    @Override
    public void run() {
        System.out.println("用 Thread 方法重写 run 方法创建线程");
    }
}
