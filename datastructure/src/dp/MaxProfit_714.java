package dp;

/*
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

示例 1:

输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
注意:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
 */
public class MaxProfit_714 {

    // 在 k 为无限的版本上改
    public int maxProfit(int[] prices, int fee) {
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
                dp[i][1] = - prices[i] - fee;
                continue;

            }
            // 买入的时候计算手续费
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);// 不能连续买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee); // 上一行已经求出，所以这里不会移除
//            }
        }

        int ret = dp[n - 1][0]; // 最大收益应该是手里没有股票了

        return ret;
    }
}
