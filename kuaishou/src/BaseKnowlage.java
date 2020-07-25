public class BaseKnowlage {

    public static String test() {
        try {
            System.out.println("aaaa");
            return "a";

        } finally {
            return "b";
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
