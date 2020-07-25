package year2019;

import java.util.ArrayDeque;
import java.util.Scanner;

/*
一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。


输入描述:
12可以解码成“AB”，“L”这两种

输出描述:
解码方法的总数

输入例子1:
12

输出例子1:
2

例子说明1:
12可以解码成“AB”，“A，B"这两种


递归的思路：
backtack(i:) = backtrack(i+1:)
若 i 和 i + 1 能组成 10~26 backtack(i:) = backtrack(i+1:) + backtrack(i + 2:)
 */
public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();

        int n = input.length();

        if (n <= 1) {
            System.out.println(n);
            return;
        }

        char[] chs = input.toCharArray();

        int ret = help(chs, 0);

        System.out.println(ret);

        // 用栈来做
//        ArrayDeque<Character> stack = new ArrayDeque<>();
//        char[] chs = input.toCharArray();
//
//        stack.push(chs[0]);
//        // 结果只与 两个数字的对数有关
//        int ret = 1;
//        for (int i = 1; i < chs.length; i++) {
//            char cur = chs[i];
//
//            if (cur > '6') {
//                Character top = stack.poll();
//                if (top == '1') {
//                    if (!stack.isEmpty() && stack.peek() > '2') {
//                        ret *= 2;
//                    } else {
//                        ret++;
//                    }
//                }
//                stack.push(top);
//            } else {
//                // cur = 1~6
//                Character top = stack.poll();
//                if (top == '1' || top == '2') {
//                    if (!stack.isEmpty() && stack.peek() > '2') {
//                        ret *= 2;
//                    } else {
//                        ret++;
//                    }
//                }
//                stack.push(top);
//            }
//
//            stack.push(cur);
//        }
//
//        System.out.println(ret);


//        int[] dp = new int[n];
//
//        dp[0] = 1;
//
//        char[] chs = input.toCharArray();
//        for (int i = 1; i < n; i++) {
//            if (chs[i - 1] == '1') {
//                dp[i] = dp[i - 1] + 1;
//            } else if (chs[i - 1] == '2' && chs[i] <= '6') {
//                dp[i] = dp[i - 1] + 1;
//            } else {
//                dp[i] = dp[i - 1];
//            }
//        }

//        int ret = 1;
//        for (int i = 0; i < input.length(); i++) {
//            if (input.charAt(i) > '2') {
//                // 当前位置只能保持单个数字编码
//            } else if (input.charAt(i) <= '2') {
//                if (input.charAt(i) == '1' && (i + 1) < input.length()) {
//                    ret ++;
//                    i ++;
//                } else if (input.charAt(i) == '2' && (i + 1) < input.length()
//                        && input.charAt(i + 1) <= '6') {
//                    ret ++;
//                    i ++;
//                }
//            }
//        }

//        System.out.println(dp[n - 1]);
    }

    /**
     * 求 从 i 到最后的部分组成编码个数
     * @param chs
     * @param i
     * @return
     */
    private static int help(char[] chs, int i) {
        if (i >= chs.length) {
            return 1;
        }

        int ret = help(chs, i + 1);
        if (i + 1 < chs.length) {
            if (chs[i] == '1' || (chs[i] == '2' && chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
                ret += help(chs, i + 2);
            }
        }

        return ret;
    }
}
