package mustlearn;

public class StringOp {

    // 字符串转整数
    public static int getInt(String s) {

        int n = 0;

        // 这里的处理逻辑其实就是百位数 被乘了 3 次 10
        // 其他位同理推
        for (int i = 0; i < s.length(); i ++) {
            n = n * 10 + (s.charAt(i) - '0');
        }

        return n;
    }

    public static void main(String[] args) {
        System.out.println(getInt("455"));
    }
}
