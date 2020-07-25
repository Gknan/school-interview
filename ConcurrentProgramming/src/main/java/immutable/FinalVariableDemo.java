package immutable;

public class FinalVariableDemo {

    private static final int a;

//    public FinalVariableDemo(int a) {
//        this.a = a;
//    }

    static {
        a = 7;
    }

    void testFinal() {
        final int b;
//        int c = b;
    }
}
