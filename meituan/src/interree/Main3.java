package interree;

/*
一个序列是有趣的需要满足：当且仅当这个序列的每一个元素ai 满足 i 整除ai 。

现在给定一个长度为n的数组，问这个数组有多少个非空的子序列是有趣的，由于答案可能比较大，只需要输出在模998244353意义下答案的就行了。

一个长度为n的数组的非空子序列定义为从这个数组中移除至多n-1个元素后剩下的元素有序按照原顺序形成的数组
。比如说对于数组[3,2,1]，它的非空子序列有[3],[2],[1],[3,2],[3,1],[2,1],[3,2,1]。

第一行一个整数n表示序列的长度。(1<=n<=1e5)
第二行n个整数表示给定的序列。(1<=ai<=1e6)
输出
输出一个数表示有趣的子序列的个数。


样例输入
2
3 1
样例输出
2

 */
import java.util.Arrays;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int [] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
        }

//        int[] arr = {3, 1};
//        int n = 2;

        boolean[] b = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i % arr[i] == 0) b[i] = true;
        }

        System.out.println(Arrays.toString(b));

        int[] help = new int[n];// 存放有 一共 i 个元素的包含的子序列
        help[0] = 1;
        for (int i = 1; i < n; i++) {
            help[i] = (1 + 2 * help[i - 1]) % 998244353;
        }

        System.out.println(Arrays.toString(help));

        int[] sum = new int[n];// 以 i 开头的有效个数
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i; j < n; j++) {
//                if (b[j] == true) {
//                    cnt ++;
//                } else {
//                    // i ~j-1 终止
//                }
                if (b[j] == false) {
                    sum[i] = help[j - i];
                    i = j;
                    break;
                }
            }
            // 全部通过
            sum[i] = cnt;
        }

        System.out.println(Arrays.toString(sum));

        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret += sum[i];
            ret %= 998244353;
        }

        System.out.println(ret);

        // 统计所有子序列，并计数
//        help(arr, 0);
    }

//    private static void help(int[] arr, int idx) {
//
//    }

    static int max = Integer.MIN_VALUE;
}
