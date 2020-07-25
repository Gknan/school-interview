package threadcoreknowledge.tradeoff;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>发布逸出</h1>
 * 副本解决
 */
public class MutiThreadError3 {

    private Map<String, String> states;

    public MutiThreadError3() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getState() {
        return states;
    }

    // 副本解决
    public Map<String, String> getStateImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MutiThreadError3 mte = new MutiThreadError3();
//        Map<String, String> states = mte.getState();
//        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));

        System.out.println(mte.getStateImproved().get("1"));
        System.out.println(mte.getStateImproved().remove("1"));
        System.out.println(mte.getStateImproved().get("1"));
    }
}
