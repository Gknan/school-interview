package year2019;

/*
题目描述
Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。现在小Y使用1024元的纸币购买了一件价
值为N (0 < N \le 1024)N(0<N≤1024)的商品，请问最少他会收到多少硬币？

输入描述:
一行，包含一个数N。
输出描述:
一行，包含一个数，表示最少收到的多少枚硬币。

输出结果应该是不唯一，因为 64 元可以换成16元和 4 元的硬币
姑且认为在能召开的情况下，返回最小个数

备注:
对于100%的数据，N (0 < N \le 1024)N(0<N≤1024)。

分析：
remain = 1024  - N
因为零钱存在着倍数关系，所以按照贪心算法，先计算能找的64元的个数；再按16元面值找。。。
累计所有的硬币个数

优化：
面值都是2的倍数；考虑使用位运算优化

为什么运行时间大，可能是因为加了一个函数，函数信息的保存本身也是占据信息的

考虑改成动态规划：
定义状态表示：dp[i] 0=<i<=remain 表示找 i 元钱，最少需要的硬币数
money[] = {1, 4, 16, 64}
状态转移方程：当前状态依赖于前一个状态
dp[i] = min(dp[i - money[k]] + 1, dp[i]);

结果存在 dp[num] 中

 */

import java.util.Scanner;

public class CashRemain {

    private static int cashRemain(int remain) {

        int cnt = 0;
//        while (remain >= 64) {
//            cnt ++;
//            remain -= 64;
//        }
//        cnt += remain / 64;
//        remain %= 64;

        cnt += remain >> 6;
        remain &= 63; // mod : a % (2^n) = a & (2^n - 1)
//        remain %= 64;

//        cnt += remain / 16;
        cnt += remain >> 4;
        remain &= 15;
//        remain %= 16;

//        cnt += remain / 4;
        cnt += remain >> 2;
        remain &= 3;
//        remain %= 4;

//        while (remain >= 16) {
//            cnt ++;
//            remain -= 16;
//        }
//
//        while (remain >= 4) {
//            cnt ++;
//            remain -= 4;
//        }

        return cnt + remain;
    }

    public static void main(String[] args) {
//        int price = 200;

        Scanner sc = new Scanner(System.in);
        int price = sc.nextInt();
        int remain = 1024 - price;
        int cnt = 0;

        cnt += remain >> 6;
        remain &= ((1<<6)- 1); // mod : a % (2^n) = a & (2^n - 1)
//        remain %= 64;

//        cnt += remain / 16;
        cnt += remain >> 4;
        remain &= ((1<<4) - 1);
//        remain %= 16;

//        cnt += remain / 4;
        cnt += remain >> 2;
        remain &= ((1<<2) - 1);

        System.out.println(cnt + remain);

//        int x = 65;
//        System.out.println(x >> 6);
//        System.out.println(x);
    }

}
