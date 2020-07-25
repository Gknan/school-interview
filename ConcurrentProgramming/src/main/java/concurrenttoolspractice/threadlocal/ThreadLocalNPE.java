package concurrenttoolspractice.threadlocal;

public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    // long 报 NPE 异常 Long 返回 null 异常
    // 拆箱，尝试将 null 转为 long 类型，就会报 NPE
    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });

        thread.start();

//        threadLocalNPE.set();
//        System.out.println(threadLocalNPE.get());
    }

}
