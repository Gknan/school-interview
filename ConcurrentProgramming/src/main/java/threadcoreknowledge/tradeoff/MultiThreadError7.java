package threadcoreknowledge.tradeoff;

/**
 * <h1>观察者模式</h1>
 * 带来的问题，如果对象初始化还没有完成，匿名内部类可以访问类中的属性并修改，
 * 监听器获取到的数据和对象初始化之后的数据不一致。
 *
 * 用工厂模式修复初始化问题
 */
public class MultiThreadError7 {

    static int count;

    private EventListener listener;

    // 不向外提供构造方法
    private MultiThreadError7(MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是：" + count);
            }
        };

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }

        count = 100;
    }

    // 不向外界提供构造函数，在一个方法中完整完成构造的过程后，再暴露出去
    public static MultiThreadError7 getInstance(MySource source) {
        // 只是创建 Listner 并没有注册
        MultiThreadError7 safeListner = new MultiThreadError7(source);
        // 完成了初始化工作后才发布
        // 创建完成后才注册
        source.registerListener(safeListner.listener);
        return safeListner;
    }

    public static void main(String[] args) {
        MySource source = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                source.eventCome(new Event() {
                });
            }
        }).start();

        MultiThreadError7 mte = MultiThreadError7.getInstance(source);
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
