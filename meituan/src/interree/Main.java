package interree;

import java.util.Arrays;
import java.util.Scanner;

/*
题目描述：
首先给出你一个整数，可能为正也可能为负，
这个数字中仅包含数字1-9，现在定义一个1-9的置换，即指定将整数中的某个数
字按顺序变换为另一个数字，请你输出变换以后的数字是多少。

输入
 输入第一行包含一个整数a。(-10^1000<=a<=10^1000)

 输入第二行包含9个以空格隔开的整数a_i , 第i个整数表示将数字i替换为数字a_i。(1<=a_i<=9)

输出
请你输出数字变换之后的结果。


样例输入
1234567
9 8 7 6 5 4 3 2 1
样例输出
9876543

提示
输入样例2
-12
2 3 7 6 5 4 3 2 1

输出样例2
-23

输入样例3
73598793378342493
1 3 6 1 6 8 9 1 3

输出样例3
96631936691613136

 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String val = scanner.nextLine();
        int[] map = new int[10];
        for (int i = 1; i < 10; i++) {
            int v = scanner.nextInt();
            map[i] = v;// 数字 i 对应的是 map[i];
        }

//        int[] map = {
//            0, 9, 8, 7, 6, 5, 4, 3, 2, 1
//        };
//
//        String val = "1234567";

        char[] chs = val.toCharArray();
//        System.out.println(Arrays.toString(chs));
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= '1' && chs[i] <= '9') {
                int x = Integer.parseInt(chs[i] + "");
//            System.out.println(x);
                chs[i] = Character.forDigit(map[x], 10);
            }
        }

//        System.out.println(Arrays.toString(chs));

        System.out.println(new String(chs));
    }
}
