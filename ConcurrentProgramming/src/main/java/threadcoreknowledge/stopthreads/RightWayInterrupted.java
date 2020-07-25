package threadcoreknowledge.stopthreads;

/**
 * <h1>注意 Thread.interrupted() 方法的目标对象是当前线程，而不管方法来自于那个对象 </h1>
 *
 * 若线程运行完毕，则不存在中断，会自动设置中断为 false
 * 若没有开启线程，也不能中断
 *
 * 静态方法 interrupted 与调用该方法的对象无关，影响的是当前执行的线程；
 * 另外两个非静态方法操作的而是当前执行的线程
 *
 *
 */
public class RightWayInterrupted {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (;;) {}
            }
        });

        // 没有开启线程
        t.start();

        // t 调用 interrupt 方法，给这行代码所在的线程（当前线程设置中断标志）
        t.interrupt();

        // 检查 线程 t 是否中断 false
        System.out.println("t isInterrupted: " + t.isInterrupted());

        // false
        System.out.println("t isInterrupted: " + t.interrupted());

        // true
        System.out.println("t isInterrupted: " + Thread.interrupted());

        // false
        System.out.println("t isInterrupted: " + t.isInterrupted());
    }
}
