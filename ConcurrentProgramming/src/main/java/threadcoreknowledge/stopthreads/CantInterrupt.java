package threadcoreknowledge.stopthreads;

/**
 * <h1>如果 while 里面放 interrupt，会导致 中断失效</h1>
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是 100 的倍数");
                }
                num ++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(5000);
        t.interrupt();
    }
}
