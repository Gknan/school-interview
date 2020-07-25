package classloadandinit;

import java.util.UUID;

public class CompilerAndRuntimeFinalConst {
    public static void main(String[] args) {
        System.out.println(Father03.str);
    }
}

class Father03 {
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("Father03 static block");
    }
}