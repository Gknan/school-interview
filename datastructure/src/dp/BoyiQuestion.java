package dp;

/*
有 N 对石头，两个聪明的人拿，轮流拿
每次只能取两边的石头；问最终 拿的多的人比拿的少的人多多少？

动态规划：
dp[i][j][k]  0<=i<n    i<=j<n  k = 0,1 0 1 标识先拿/后拿得到的最多  dp[i][j][0] 表示对于 i~j 堆石头，先拿的人最多获得的石头数字

递推方程：
先拿的人拿了第 i 堆，在剩余的 i+1~j 堆石头中，就变成了后拿的人 两者之间取最大
所以有 dp[i][j][0] = max(dp[i+1][j][1] + piles[i], dp[i][j-1][1] + piles)
对应的后拿的人，若先拿的人取了 第 i 堆，dp[i][j][1] = dp[i + 1][j][0];否则 dp[i][j][1] = dp[i][j - 1][0]
base case:  当 i==j 时，只有一堆石头，先拿的人 dp[][][0] = piles[] ，后拿的人拿到的是 0

更新时是按照 下边和左边来更新的，所以填表应该是按照对角线或者 逆序填表都可以


 */
public class BoyiQuestion {

    public int getAnwser(int[] piles) {

        int n = piles.length;
        int[][][] dp = new int[n][n][2];

        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = piles[i];
            dp[i][i][1] = 0;
        }

        // 从n-1 n-1 往上填表
        for (int i = n - 1; i >= 0; i--) {
            // 每一行从左往右
            for (int j = i + 1; j < n; j++) {
//                dp[i][j][0] = Math.max(dp[i + 1][j][0] + piles[i], dp[i][j - 1][0] + piles[i]);
//                dp[i][j][1] = dp[i][j][0] == dp[i + 1][j][0] + piles[i] ? dp[i + 1][j][1] : dp[i][j - 1][1];
                dp[i][j][0] = Math.max(dp[i + 1][j][1] + piles[i], dp[i][j - 1][1] + piles[i]);
                dp[i][j][1] = dp[i][j][0] == dp[i + 1][j][1] + piles[i] ? dp[i + 1][j][0] : dp[i][j - 1][0];
            }
        }

        return dp[0][n - 1][0] - dp[0][n - 1][1];
    }
}
