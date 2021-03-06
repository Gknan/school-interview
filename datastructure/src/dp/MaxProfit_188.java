package dp;

/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2:

输入: [3,2,6,5,0,3], k = 2
输出: 7
解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

分析：

dp[i][j][k]
dp[i][j][0] 表示 第 i 天，还可以交易 k 笔，选择卖出股票的最大收益；
dp[i][j][1] 表示 第 i 天，还可以交易 k 笔，选择持有股票的最大收益；
dp[i][j][2] 表示 第 i 天，还可以交易 k 笔，选择买进股票的最大收益；

定义
dp[i][j][o or 1] 标识 第 i 天，最多允许交易 j 笔，当前手里持有股票(0) 或者 没有持有股票(1) 的最大收益
列出状态机会

第i 天持有股票：第 i -1 天就持有 或者 是 第 i - 1 天本来没有，然后买入了股票
第 i 天没哟股票：第 i - 1 天没有 或者 前一天有然后卖了

base case
dp[-1][k][0] 0 还没开始，利润是 0
dp[-1][k][1] -INF 还没开始，不可能有交易，利润是 负无穷

dp[i][0][0]=0 k>= 1 不允许交易，利润为0
dp[i][0][1] = -INF 非法值，使用负无穷


 */
public class MaxProfit_188 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // k 标识最多完成几笔交易，卖出时更新 k
        int[][][] dp = new int[n][k + 1][2]; // n 天 k 可交易次数 0 没有持有股票 1 持有股票 收益

        if (k > n/2) {
            // k 为 无限
            return maxProfit_kinf(prices);
        }
        // 递推方程
        for (int i = 0; i < n; i++) {
            for (int j = k; j > 0; j--) { // 由于当前位置的值与左上和正上决定；所以 在第一行决定后，k 逆序求 另外，数组 k 应该大小为 k + 1,保证
                // 所有的 k 都求到
                // 边界处理
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

    // 尽可能多卖，k 看做无限
    public int maxProfit_kinf(int[] prices) {

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
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);// 不能连续买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 上一行已经求出，所以这里不会移除
//            }
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
//                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
//                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    // i = 0 时，dp[-1][j][0] 0  dp[- 1][j][1] -∞
                    // dp[-1][j - 1][0] = 0 还没交易，
                    dp[i][j][0] = 0;
                    dp[i][j][1] = - prices[i];
                    continue;

                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);// 不能连续买入
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]); // 上一行已经求出，所以这里不会移除
            }
        }

        int ret = dp[n - 1][k][0]; // 最大收益应该是手里没有股票了
    }
}
