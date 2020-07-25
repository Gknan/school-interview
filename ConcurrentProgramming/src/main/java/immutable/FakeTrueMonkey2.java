package immutable;

/**
 * 是否是编译器可确定，编译器可确定，常量池
 * 运行时确定，生成在堆中
 * final 修饰的常量，编译期间可以确定，就直接把对应的值写到需要的位置的代码上
 */
public class FakeTrueMonkey2 {

    public static void main(String[] args) {
        String a = "wukong2";// 编译器确定，在常量池中
        final String b = getDashixiong();
//        String d = "wukong";
        String c = b + 2; //
//        String e = d + 2; // e 是新的字符串指向新的地址 运行时确定，生成在堆上
        // String 重写了 equals 方法 == 判断的是地址
        System.out.println(a == c);
//        System.out.println(a == e);
    }

    private static String getDashixiong() {
        return "wukong";
    }
}
