package top100;

import java.util.Arrays;

/*
给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，
你都可以从 + 或 -中选择一个符号添加在前面。

返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

示例 1:

输入: nums: [1, 1, 1, 1, 1], S: 3
输出: 5
解释:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

一共有5种方法让最终目标和为3。
注意:

数组非空，且长度不会超过20。
初始的数组的和不会超过1000。
保证返回的最终结果能被32位整数存下。

分析：
先写递归版本，时间复杂度 O(2^n)
记录每个位置开始到末尾的和 差，若当前位置的 preSum + sum[start+1:] > S |
 preSum + diff[start + 1:] < S，肯定无解，结束循环

dp[i][j] 表示前 i 个数，组成和为 j 的方案数
dp[i][j] = dp[i - 1][j - nums[i]] + dp[i-1][j + nums[i]]
dp[i][j + nums[i]] += dp[i - 1][j]
dp[i][j - nums[i]] += dp[i - 1][j]
和小于 1000，所以 -1000~1000


 */
public class FindTargetSumWays_494 {

    public int findTargetSumWays2(int[] nums, int S) {
        if (nums.length < 1) return 0;

        int[][] dp = new int[nums.length][2001];


        dp[0][nums[0] + 1000] ++;
        dp[0][- nums[0] + 1000] ++;

        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
//                if (i == 0) {
//                    dp[i][nums[i] + 1000] ++;
//                    dp[i][- nums[i] + 1000] ++;
//                    continue;
//                }
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }

        return dp[nums.length - 1][S + 1000];
    }

    int cnt = 0;
    public int findTargetSumWays(int[] nums, int S) {

        if (nums.length < 1) return 0;

        // 排序不必须要，因为是找到最后一个位置才能确定
//        Arrays.sort(nums);
        int minusSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            minusSum -= nums[i];
        }

//        dfs(nums, S, 0, 0);
        dfs(nums, S, 0, 0, minusSum, sum);

        return cnt;

    }

    private void dfs(int[] nums, int s, int preSum, int start,
                     int minusSum, int sum) {
        // 剪枝
//        if (preSum + sum > s || preSum + minusSum < s) return;
        // preSim = 4，s = 3，sum = 1，minus = -1
        // 全部加也小于 s 或者 全部减也大于 s，全部减也在s之前
        if (preSum + sum < s || preSum + minusSum > s) return;

        if (start == nums.length) {
            if (preSum == s) cnt ++;
            return;
        }

        dfs(nums, s, preSum - nums[start],start + 1,
                minusSum + nums[start], sum - nums[start]);
        dfs(nums, s, preSum + nums[start], start + 1,
                minusSum + nums[start], sum - nums[start]);
    }

    /**
     * 当期下标 start，截止目前找到的值
     * @param nums
     * @param s
     * @param preSum
     * @Param start
     */
    private void dfs(int[] nums, int s, int preSum, int start) {

        if (start == nums.length) {
            if (preSum == s) cnt ++;
            return;
        }

        dfs(nums, s, preSum - nums[start], start + 1);
        dfs(nums, s, preSum + nums[start], start + 1);

    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int s = 3;

        System.out.println(new FindTargetSumWays_494().findTargetSumWays(nums, s));
        System.out.println(new FindTargetSumWays_494().findTargetSumWays2(nums, s));
    }
}
