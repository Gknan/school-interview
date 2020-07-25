package future;

import java.util.concurrent.*;

/**
 * get 的超时方法
 * 超时后需要处理，调用 Future.cancel
 * 将会演示 cancel 传入 true 和 false 的区别
 * 代表是否中断正在执行的任务
 */
public class GetTimeoutException {

    private static final Ad DEFAULTAD = new Ad("无网络时的广告");

    private static final ExecutorService service =
            Executors.newFixedThreadPool(10);

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Sleep 期间被中断了");
                return new Ad("被中断了，默认广告");
            } finally {

            }
            return new Ad("旅游订票哪家强");

        }
    }

    public void printAd() {
        Future<Ad> future = service.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断了，默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常了，默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = future.cancel(false);
            // 捕获异常后一定要取消任务
//            boolean cancel = future.cancel(true);
            // 线程会收到中断信号
            System.out.println("cancel的结果：" + cancel);
        } finally {
            service.shutdown();
        }
        System.out.println(ad);
    }

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        GetTimeoutException timeoutException = new GetTimeoutException();
        timeoutException.printAd();
    }

}
