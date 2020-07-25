package dp;

/*
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。



示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit_121 {
    public int maxProfit(int[] prices) {

        // 一买一卖需要2天，所以交易次数 k 小于 n/2 说明正常；若 k 大于，不要求每天只能买或卖一次 按照 k 不限来处理
        int n = prices.length;
        if (n < 2) return 0;

//        int k = 1;
        // k 标识最多完成几笔交易，卖出时更新 k
        int[][] dp = new int[n][2]; // n 天 k 可交易次数 0 没有持有股票 1 持有股票 收益

        // 递推方程
        for (int i = 0; i < n; i++) {
//            for (int j = k; j > 0; j--) { // 由于当前位置的值与左上和正上决定；所以 在第一行决定后，k 逆序求 另外，数组 k 应该大小为 k + 1,保证
//                // 所有的 k 都求到
//                // 边界处理
                if (i == 0) {
//                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
//                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    // i = 0 时，dp[-1][j][0] 0  dp[- 1][j][1] -∞
                    // dp[-1][j - 1][0] = 0 还没交易，
                    dp[i][0] = 0;
                    dp[i][1] = - prices[i];
                    continue;

                }
//                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
//                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]); // 上一行已经求出，所以这里不会移除
//            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);// 不能连续买入
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]); // 上一行已经求出，所以这里不会移除
        }

        int ret = dp[n - 1][0]; // 最大收益应该是手里没有股票了

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
                    continue;

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
