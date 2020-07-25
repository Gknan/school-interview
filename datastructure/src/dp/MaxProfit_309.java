package dp;

/*
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class MaxProfit_309 {
    // 在 k 为无限的版本上改
    public int maxProfit(int[] prices) {
        // 一买一卖需要2天，所以交易次数 k 小于 n/2 说明正常；若 k 大于，不要求每天只能买或卖一次 按照 k 不限来处理
        int n = prices.length;

        if (prices.length < 2) return 0;

        // k 标识最多完成几笔交易，卖出时更新 k
        int[][] dp = new int[n][2]; // n 天 k 可交易次数 0 没有持有股票 1 持有股票 收益

        // 递推方程
        for (int i = 0; i < n; i++) {
//            for (int j = k; j > 0; j--) { // 由于当前位置的值与左上和正上决定；所以 在第一行决定后，k 逆序求 另外，数组 k 应该大小为 k + 1,保证
            // 所有的 k 都求到
            // 边界处理
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = - prices[i];
                continue;

            }
            if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1],  - prices[i]); // 上次卖出和这次买入至少相差一天
                continue;
            }
            // i = 1 dp[1][0] =
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);// 不能连续买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]); // 上次卖出和这次买入至少相差一天
//            }
        }

        int ret = dp[n - 1][0]; // 最大收益应该是手里没有股票了

        return ret;
    }
}
