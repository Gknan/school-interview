package lock;

public class TestReturnFinally {

    public static void main(String[] args) {

        try {
            boolean interrupted = false;
            for (int i = 0; i < 100; i ++) {
                if (i == 10) {
                    System.out.println("for.....");
                    return;
                }
            }
        } finally {
            // 说明这段代码是在 return 之前的代码里执行的
            System.out.println("finally.....");
        }
    }
}
