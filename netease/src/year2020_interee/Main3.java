package year2020_interee;

import java.util.Scanner;

/**
 * 小易是班级的英语课代表, 他开发了一款软件开处理他的工作。
 * 小易的软件有一个神奇的功能，能够通过一个百分数来反应你的成绩在班上的位置。“成绩超过班级 ...% 的同学”。
 * 设这个百分数为 p，考了 s 分，则可以通过以下式子计算得出 p：
 * p = ( 分数不超过 s 的人数 - 1)  班级总人数
 * 突然一天的英语考试之后，软件突然罢工了，这可忙坏了小易。成绩输入这些对于字写得又快又好的小易当然没有问题，但是计算这些百分数……这庞大的数据量吓坏了他。
 * 于是他来找到你，希望他编一个程序模拟这个软件：给出班级人数 n，以及每个人的成绩，请求出某几位同学的百分数。
 *
 * 输入描述:
 * 第一行一个整数 n，表示班级人数。
 * 第二行共 n 个自然数，第 i 个数表示第 i 位同学的成绩 a_i。
 * 第三行一个整数 q，表示询问的次数。
 * 接下来 q 行，每行一个数 x，表示询问第 x 位同学的百分数。
 *
 *
 * 输出描述:
 * 输出应有 q 行，每行一个百分数，对应每一次的询问。
 *
 * 为了方便，不需要输出百分号，只需要输出百分号前的数字即可。四舍五入保留六位小数即可。
 *
 * 输入例子1:
 * 3
 * 100 98 87
 * 3
 * 1
 * 2
 * 3
 *
 * 输出例子1:
 * 66.666667
 * 33.333333
 * 0.000000
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] score = new int[n];
        int[] nums = new int[151];
        for (int i = 0; i < score.length; i++) {
            score[i] = scanner.nextInt();
            nums[score[i]] ++; // 统计对应分数的人数
        }

        // 更新 nums 数组
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] += sum;
            sum = nums[i];
        }



        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int idx = scanner.nextInt();
            // 两个 int 型的数据相除的处理，其中一个 * 1.0 这种，就可以转换成 double 形式处理
            double ret = (nums[score[idx - 1]] - 1) * 1.0 * 100 / n;
            System.out.println(String.format("%.6f", ret));
        }
    }
}
