package threadcoreknowledge.createthreads.wrongways;

/**
 * <h1>匿名内部类创建线程</h1>
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("匿名内部类1.。。。。");
            }
        }.start();
    }
}
