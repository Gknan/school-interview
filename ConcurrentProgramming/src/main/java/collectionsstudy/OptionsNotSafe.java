package collectionsstudy;

import java.util.concurrent.ConcurrentHashMap;

public class OptionsNotSafe implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
//            synchronized (OptionsNotSafe.class) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                boolean b = scores.replace("小明", score, newScore);
                if (b) break;
            }
//                scores.put("小明", newScore);
//            }
        }
    }

    private static ConcurrentHashMap<String, Integer> scores =
            new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);

        Thread t1 = new Thread(new OptionsNotSafe());
        Thread t2 = new Thread(new OptionsNotSafe());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(scores.get("小明"));
    }
}
