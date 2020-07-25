package threadcoreknowledge.stopthreads;

/**
 * <h1>错误的停止方法</h1>
 * 用 stop 停止线程，会导致线程运行一半突然停止，没办法模拟基本单位的
 * 操作(一个连队是一个单位)。会造成脏数据（同一个连队有人没有领取到）。
 */
public class StopThread implements Runnable {


    @Override
    public void run() {
        // 模拟指挥军队：一共 5 个连队，每个连队 100 人，以连队为单位，发放武器弹药，
        // 叫到号的士兵前去领取
        for (int i = 0; i < 5; i++) {
            System.out.println("连队" + i + "开始领取武器");
            for (int j = 0; j < 10; j ++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "已经领取完毕");
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new StopThread());

        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.stop();
    }
}
