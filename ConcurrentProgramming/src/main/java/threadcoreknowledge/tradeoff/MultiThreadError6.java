package threadcoreknowledge.tradeoff;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>在构造函数中新开线程初始化/h1>
 *
 */
public class MultiThreadError6 {

    private Map<String, String> states;

    public MultiThreadError6() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1", "周一");
                states.put("2", "周二");
                states.put("3", "周三");
                states.put("4", "周四");
            }
        }).start();
    }

    public Map<String, String> getState() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError6 mte = new MultiThreadError6();

//        Thread.sleep(1000);
        // 构造函数中的子线程执行是需要时间的，初始化工作还没有完成，此时去获取，抛出 NPE 异常
        Map<String, String> states = mte.getState();
        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));
    }
}
