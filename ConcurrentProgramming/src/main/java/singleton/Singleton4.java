package singleton;

/**
 * <h1>懒汉式（线程安全）不推荐</h1> 需要用的时候加载
 *
 * 问题：
 * 如果多个线程竞争，都执行到 getInstance，由于 synchronized 重量级，
 * 效率低下
 */
public class Singleton4 {

    private static Singleton4 INSTANCE;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}
