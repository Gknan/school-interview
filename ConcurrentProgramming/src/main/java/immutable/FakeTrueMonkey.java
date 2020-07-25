package immutable;

public class FakeTrueMonkey {

    public static void main(String[] args) {
        String a = "wukong2";// 编译器确定，在常量池中
        final String b = "wukong"; // 当做常量看待
        String d = "wukong";
        String c = b + 2; //
        String e = d + 2; // e 是新的字符串指向新的地址 运行时确定，生成在堆上
        // String 重写了 equals 方法 == 判断的是地址
        System.out.println(a == c);
        System.out.println(a == e);
    }
}
