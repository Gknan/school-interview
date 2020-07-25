package singleton;

/**
 * <h1>双重检查</h1> 推荐面试时使用 双重检查
 * <p>
 * 好处：
 * 1，线程安全
 * 2，延迟加载，效率较高
 * <p>
 * 问题：
 * 为什么要 double-check
 * 1，线程安全
 * 2，单 check 行不行？
 * 3，放到方法上，性能问题
 */
public class Singleton6 {

    private volatile static Singleton6 INSTANCE;
    // volatile 的使用，新建对象的非原子性

    // 构造函数私有是必须的，如果不写构造方法，程序会提供默认的无参构造，这时候就能直接创造对象了
    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (INSTANCE == null) {
//            synchronized (this) {
            synchronized (Singleton6.class) {
                if (INSTANCE == null) {// 如果没有这个，第二个线程进入 同步块，还会再创建一个，覆盖前面的
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }

}
