package dp;

/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
示例 2:

输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。

dp[i] 从 i 开始，偷得的最大值
 */
public class RobHouse_213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0],nums[1]);
//        if (n == 3) return nums[1];

        // 第一件和最后一间不能同时偷，选择大的一方
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    public int robRange(int[] nums, int left, int right) {
        int n = nums.length;
        // 第一件和最后一间不能同时偷，选择大的一方
        int[] dp = new int[n + 1];

        dp[left] = nums[left];
        dp[left + 1] = Math.max(nums[left], nums[left + 1]);

        for (int i = left + 2; i <= right; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[right];
    }

}
