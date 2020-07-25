package singleton;

/**
 * <h1>懒汉式（线程不安全）</h1> 需要用的时候加载
 *
 * 问题：
 * 假设两个线程都在执行到 if (INSTANCE == null) ，第一个线程抢到锁，创建实例，释放锁，第二个线程
 * 得到锁，继续创建
 */
public class Singleton5 {

    private static Singleton5 INSTANCE;

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton5.class) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
