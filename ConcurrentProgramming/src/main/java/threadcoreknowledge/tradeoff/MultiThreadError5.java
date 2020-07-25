package threadcoreknowledge.tradeoff;

/**
 * <h1>观察者模式</h1>
 * 带来的问题，如果对象初始化还没有完成，匿名内部类可以访问类中的属性并修改，
 * 监听器获取到的数据和对象初始化之后的数据不一致。
 */
public class MultiThreadError5 {

    static int count;

//    {
//        System.out.println("我是 main 的代码块");
//    }

    // 调用了主类的 静态方法，主动使用的场景，主类被初始化，但是没有实例化
    public MultiThreadError5(MySource source) {
        // 本类向匿名内部价类暴露了自己的引用，匿名内部类持有自己的引用
        // 当构造没有完成，匿名内部类可以访问引用中的值
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是：" + count);
            }
        });

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
            // 在执行这里的过程中，子线程执行了 source.eventCome(new Event() {}) 方法 实际上赋值还没有完成
        }

        count = 100;
    }

    public static void main(String[] args) {
        // 执行 main 方法时，主类完成了初始化，惊天属性完成赋值 count = 0
        MySource source = new MySource(); // 初始化，实例化一个 source 对象，调用的是默认的无参构造函数
        new Thread(new Runnable() { // 启动一个子线程，hb 原则，对于启动之前的结果可见
            @Override
            public void run() {
                try {
                    // 等待 10 ms
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 调用 srouce 实例的 eventCome 方法，新建 Event 实例并传入 Event 是接口，没有实现类，这里相等于匿名内不类的方创建了 Event 实例
                source.eventCome(new Event() {
                });
            }
        }).start();

        // 实例化 MultiThreadError5 类 执行实例属性 代码块等的初始化和赋值，完成后执行构造函数
        MultiThreadError5 mte = new MultiThreadError5(source);
//        System.out.println("==========");
//        System.out.println(count);
    }

    static class MySource {

        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

    }

    interface EventListener {

        void onEvent(Event e);
    }

    interface Event {

    }
}
