package singleton;

/**
 * <h1>饿汉式（静态代码块）（可用）</h1>
 * 优点：
 * 1，简单
 * 2，类装载的时候就完成了实例化 static 避免了线程同步问题
 */
public class Singleton2 {

    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
