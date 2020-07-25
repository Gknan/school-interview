package deadlock;

import java.util.Random;

/**
 * 演示活锁问题
 */
public class LiveLock {

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        Spoon spoon = new Spoon(husband);

        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon, wife);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon, husband);
            }
        }).start();
    }

    static class Spoon {
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten!\n", owner.name);
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry = true;

        public Diner(String name) {
            this.name = name;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                // 加入随机因素 解决活锁问题
                Random rand = new Random();
                if (spouse.isHungry && rand.nextInt(10) < 9) {
                    System.out.println(name + ": 你先" + spouse.name);
                    spoon.setOwner(spouse);
                    continue;
                }

                // 重试机制不变，而且没有限制重试次数
//                if (spouse.isHungry) {
//                    System.out.println(name + ": 你先" + spouse.name);
//                    spoon.setOwner(spouse);
//                    continue;
//                }

                spoon.use();
                isHungry = false;
                System.out.println(name + ": 吃饱了");
                spoon.setOwner(spouse);
            }
        }
    }
}
