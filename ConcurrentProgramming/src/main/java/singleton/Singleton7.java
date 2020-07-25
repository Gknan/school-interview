package singleton;

/**
 * <h1>静态内部类（可用）</h1>
 *
 * 对象初始化时机：静态变量被调用
 *
 * 好处：
 * 1，线程安全
 * 2，延迟加载，效率较高
 *
 */
public class Singleton7 {

    private Singleton7() {

    }

    private static class SingletonInstance {

        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
