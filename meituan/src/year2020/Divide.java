package year2020;

/*
不用除号求两个正整数的商

使用位运算来模拟
100 / 3
位运算 3 * 2 = 6  <= 100 商为 2
6 * 2 = 12 <= 100 商为 4
这个过程中记录商
知道最后 96 * 2 > 100 跳出
100 - 96 = 4
4 继续进行上面的过程
*/
public class Divide {

    // a b 都是正整数
    public static int myDiv(int a, int b) {

        // 考虑 ab 是所有可能的整数
        if (a == 0) return 0;
        if (a == b) return 1;
        if (b == 0) return 0;

        boolean flag = false; // + - 定义
        if (a < 0 && b < 0) {
            a = -a;
            b = -b;
        } else if (a < 0 && b > 0) {
            a = -a;
            flag = true; // 答案需要翻转
        } else if (a > 0 && b < 0) {
            b = -b;
            flag = true;
        }

        int ans = 0;
        while (a >= b) {
            int x = b;
            int y = 1;
            while (a >= (x << 1)) {
                x <<= 1;
                y <<= 1;
            }

            a -= x;
            ans += y;
        }

        return flag ? -ans : ans;
    }

    public static void main(String[] args) {
        System.out.println(myDiv(-1, 3));
        System.out.println(myDiv(-100, -3));
        System.out.println(myDiv(100, -3));
        System.out.println(myDiv(0, 3));
    }

}
