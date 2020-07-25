package year2019_2;

import java.util.Arrays;
import java.util.Scanner;

/*
假设你是一位很有爱的幼儿园老师，想要给幼儿园的小朋友们一些小糖果。但是，每个孩子
最多只能给一块糖果。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃
口的糖果的最小尺寸；并且每块糖果 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将
这个糖果 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子
，并输出这个最大数值。
注意：
你可以假设胃口值为正。
一个小朋友最多只能拥有一块糖果。


输入描述:
第一行输入每个孩子的胃口值

第二行输入每个糖果的尺寸

孩子数和糖果数不超过1000

输出描述:
能满足孩子数量的最大值

输入例子1:
1 2 3
1 1

输出例子1:
1
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inStrs = scanner.nextLine().split(" ");
        String[] candyStrs = scanner.nextLine().split(" ");

        int n = inStrs.length;
        int m = candyStrs.length;

        int[] g = new int[n];
        int[] s = new int[m];
        for (int i = 0; i < n; i++) {
            g[i] = Integer.parseInt(inStrs[i]);
        }

        for (int i = 0; i < m; i++) {
            s[i] = Integer.parseInt(candyStrs[i]);
        }

        // 两个数组先排序
        Arrays.sort(g);
        Arrays.sort(s);

        boolean[] used = new boolean[m];// 一共 m 个糖果

        // s >= g 才能达到满足条件
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            // 对于 n 个孩子，找对应的可满足的糖果
            // 分出去的糖果，需要记录
            for (int j = m - 1; j >= 0; j--) {
                if (used[j]) continue;
                if (s[j] >= g[i]) {
                    cnt ++;
                    used[j] = true;
                    break;
                } else {
                    // 当前小，前面的越小，剪枝
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
