package threadcoreknowledge.threadfields;

/**
 * <h1>Id 从 1 开始，JVM 运行起来自己创建的线程 ID 早已不是1</h1>
 */
public class Id {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("子线程ID" + Thread.currentThread().getId());
            }
        });

        System.out.println("主线程ID" + Thread.currentThread().getId());

        t.start();
    }
}
