package threadcoreknowledge.stopthreads;

/**
 * <h1>run 无法抛出 checked Exception，只能 try/catch </h1>
 */
public class RunThrowException {

    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
//                throw new Exception();
            }
        });
    }
}
