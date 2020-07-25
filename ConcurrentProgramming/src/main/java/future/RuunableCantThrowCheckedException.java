package future;

/**
 * 在 run 方法中无法抛出 checked Exception
 */
public class RuunableCantThrowCheckedException {


    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    };
}
