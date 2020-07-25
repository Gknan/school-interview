package singleton;

/**
 * <h1>饿汉式（静态常量）（可用）</h1>
 * 优点：
 * 1，简单
 * 2，类装载的时候就完成了实例化 static 避免了线程同步问题
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
