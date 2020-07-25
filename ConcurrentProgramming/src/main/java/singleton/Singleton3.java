package singleton;

/**
 * <h1>懒汉式（线程不安全）</h1> 需要用的时候加载
 *
 * 问题：
 * 如果两个线程竞争，都执行到 getInstance，可能会多次创建实例
 */
public class Singleton3 {

    private static Singleton3 INSTANCE;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
