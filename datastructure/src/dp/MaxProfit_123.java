package dp;

/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1]
输出: 0
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit_123 {
    // 最多完成两笔交易，k = 2
    public int maxProfit(int[] prices) {
        // 一买一卖需要2天，所以交易次数 k 小于 n/2 说明正常；若 k 大于，不要求每天只能买或卖一次 按照 k 不限来处理

        int n = prices.length;

        if (n < 2) return 0;

        int k = 2;

        // k 标识最多完成几笔交易，卖出时更新 k
        int[][][] dp = new int[n][k + 1][2]; // n 天 k 可交易次数 0 没有持有股票 1 持有股票 收益

        // 递推方程
        for (int i = 0; i < n; i++) {
            for (int j = k; j > 0; j--) { // 由于当前位置的值与左上和正上决定；所以 在第一行决定后，k 逆序求 另外，数组 k 应该大小为 k + 1,保证
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = - prices[i];
                    continue;

                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]); // 上一行已经求出，所以这里不会移除
            }
        }

        int ret = dp[n - 1][k][0]; // 最大收益应该是手里没有股票了

        return ret;
    }

    // 算法框架
    private void frameword(int k, int[] prices) {

        // 一买一卖需要2天，所以交易次数 k 小于 n/2 说明正常；若 k 大于，不要求每天只能买或卖一次 按照 k 不限来处理

        int n = prices.length;

        // k 标识最多完成几笔交易，卖出时更新 k
        int[][][] dp = new int[n][k + 1][2]; // n 天 k 可交易次数 0 没有持有股票 1 持有股票 收益

        // init state
//        dp[-1][k][0] = 0;// 还没开始交易，收益是 0
//        dp[-1][k][1] = Integer.MIN_VALUE; // 还没交易，不可能持有股票
////
//        dp[i][0][0] = 0; // 还没交易，利润是0
//        dp[i][0][1] = Integer.MIN_VALUE; // 不合法


        // 递推方程
        for (int i = 0; i < n; i++) {
            for (int j = k; j > 0; j--) { // 由于当前位置的值与左上和正上决定；所以 在第一行决定后，k 逆序求 另外，数组 k 应该大小为 k + 1,保证
                // 所有的 k 都求到
                // 边界处理
                if (i == 0) {
//                    dp[i][j][0] = 0;
//                    dp[i][j][1] = Integer.MIN_VALUE;
//                    continue;
//                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
//                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    // i = 0 时，dp[-1][j][0] 0  dp[- 1][j][1] -∞
                    // dp[-1][j - 1][0] = 0 还没交易，
                    dp[i][j][0] = 0;
                    dp[i][j][1] = - prices[i];

                }
//                if (j == 0) {
//                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
//                    dp[i][j][1] = dp[i - 1][j][1];
//                    continue;
//                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]); // 上一行已经求出，所以这里不会移除
            }
        }

        int ret = dp[n - 1][k][0]; // 最大收益应该是手里没有股票了
    }
}
