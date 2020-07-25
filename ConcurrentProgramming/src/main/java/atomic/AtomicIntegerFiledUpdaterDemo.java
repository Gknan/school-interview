package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 演示 AtomicFiledUpdater
 */
public class AtomicIntegerFiledUpdaterDemo implements Runnable{

    static  Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
            = AtomicIntegerFieldUpdater
            .newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score ++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();
        AtomicIntegerFiledUpdaterDemo r = new AtomicIntegerFiledUpdaterDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("普通：" + peter.score);
        System.out.println("升级：" + tom.score);
    }

    public static class Candidate {
        volatile int score;
    }
}
