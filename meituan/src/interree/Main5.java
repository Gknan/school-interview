package interree;

/*
套娃最近很流行，小美想知道前缀和是否也可以进行套娃操作。

小美现在想知道能否快速求解

输入
第一行两个数N，K。

第二行N个数，第 i 个数为a[i]。

1<=N<=5000 ；1<=K<998244353；0<=a[i]<998244353

输出
一个数表示sum[K][N]。

样例输入
4 3
1 0 0 0
样例输出
10

提示
样例解释，需要三次求和：
第一次：sum[1][1] = sum[0][1] = 1, sum[1][2] = sum[0][1] + sum[0][2] = 1, sum[1][3] = sum[0][1] + sum[0][2] + sum[0][3] = 1, sum[1][4] = sum[0][1] + sum[0][2] + sum[0][3] + sum[0][4] = 1. sum[1][] = { 1, 1, 1, 1 };
第二次：sum[2][] = { 1, 2, 3, 4 }.
第三次：sum[3][] = { 1, 3, 6, 10 }.
故sum[K][N] = sum[3][4] = 10
 */
import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] dp = new int[n + 1];

//        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = scanner.nextInt();
        }

//        for (int i = 1; i <= k; i++) {
//            dp[i][1] = dp[0][1];
//        }

        // 更新 dp
        for (int i = 1; i <= k; i++) {
            int left = 0;
            for (int j = 1; j <= n; j++) {
                dp[j] = (left + dp[j]) % 1000000007;
                left = dp[j];
//                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        System.out.println(dp[n]);

    }
}
