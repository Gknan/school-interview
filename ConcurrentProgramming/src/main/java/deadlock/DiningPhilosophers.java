package deadlock;

/**
 * 哲学家就餐问题导致的死锁
 */
public class DiningPhilosophers {

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[5];

        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Object();
        }

        for (int i = 0; i < 5; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];

            // 改变一个哲学家拿叉子的顺序（避免策略）
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
//            philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }

    public static class Philosopher implements Runnable {

        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
//                    thinking;
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Pickup left chopstick");
//                        Pickup left chopstick;
                        synchronized (rightChopstick) {
                            doAction("Pickup right chopstick");
                            doAction("Eating");
                            doAction("Put down right chopstick");
//                            Pickup right chopstick;
//                            -eat;
                        }
                    }
                    doAction("Put down left chopstick");
                }
            } catch (InterruptedException e) {

            }
        }
    }

    private static void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
//        Thread.sleep((long) (Math.random() * 10));
        Thread.sleep((long) (10));
    }
}
