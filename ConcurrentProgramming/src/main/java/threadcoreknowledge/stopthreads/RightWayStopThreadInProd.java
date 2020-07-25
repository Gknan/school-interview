package threadcoreknowledge.stopthreads;

/**
 * <h1>cache 住 InterruptedException 之后优先选择在方法签名中抛出异常</h1>
 * 那么在 run 方法就会强制要求 try/catch
 */
public class RightWayStopThreadInProd implements Runnable{
    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go.....");
            try {
                throwInMethod();// 处理异常后，清空标志位，中断只有一次，后面就没有中断了
            } catch (InterruptedException e) {
                // TODO 保存日志，停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }

            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadInProd());
        t.start();

        Thread.sleep(1000);
        t.interrupt();

//        Thread.interrupted();
//        t.isInterrupted();
    }
}
