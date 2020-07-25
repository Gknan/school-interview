package top100;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。

分析：
1，暴力法：尝试所有的非报警偷法，记录结果，最终返回最大的结果
2，暴力优化法：动态规划
dp[i] 截止第 i 家，已经偷的最大金额
dp[i] = dp[i - 1] or dp[i - 1] + nums[i]

dp[i][j] 0<=i<=n j = {0, 1} dp[i][0] 表示没有偷上一家，借助目前的最大值；dp[i][1] 标识偷了上一家，目前的额最大值
dp[i][0] = max(dp[i-1][0], dp[i-1][1])
dp[i][1] = max(dp[i-1][0] + nums[i], dp[i-1][0])
初始值：dp[0] = 0 ;求 dp[n]

设计动态规划的三个步骤
1，把问题分解成最优子问题
2，用递归的方式将问题表述成最优子问题的解
3，自底向上的讲递归转化成迭代；（递归是自顶向下）

最优子问题：
对应连续的 n 栋房子：H1， H2， H3，....，Hn，小偷挑选要偷的房子，且不能偷相邻的两栋
房子，方案可分成两种：
方案一：挑选的房子中包含最后一栋
方案二：挑选的房子中不包含最后一栋
获取的最大收益的最终方案，一定是在这两种方案中产生，用代码标识就是：
最优结果 = Math.max(方案一最优结果，方案二最优结果);

递归转迭代
递归是自顶向下的，存在很多重复计算；所以采用迭代的方式，消除重复计算，过程中记忆中间得到的值

迭代版本的空间优化
每次迭代需要几个变量就只需要定义几个变量
 */
public class Rob_198 {

    public int rob(int[] nums) {

        return toRob(nums, nums.length - 1);
    }

    // 递归写法
    private int toRob(int[] nums, int idx) {
        if (idx == 0) return nums[0];
        if (idx == 1) return Math.max(nums[0], nums[1]);

        // 方案一 包含最后一栋
        int plan1 = toRob(nums, idx - 2) + nums[idx];

        // 方案二 不包含最后一栋
        int plan2 = toRob(nums, idx - 1);


        return Math.max(plan1, plan2);
    }

    // 递归改动态规划
    public int robDP(int[] nums) {
        // 忘了考虑传入的nums 为空串了
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int idx = 2; idx < nums.length; idx ++) {
            // 方案1
            int plan1 = dp[idx - 2] + nums[idx];
            // 方案2
            int plan2 = dp[idx - 1];

            dp[idx] = Math.max(plan1, plan2);
        }

        return dp[nums.length - 1];
    }

    // 对 DP 方法的空间进行优化，由于每次迭代只使用了两个变量，所以使用两个变量就可以
    public int robDP2(int[] nums) {
        // 忘了考虑传入的nums 为空串了
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

//        int[] dp = new int[nums.length];

        int lastBefBst = nums[0];
        int lastBst = Math.max(nums[0], nums[1]);

        for (int idx = 2; idx < nums.length; idx ++) {
            // 方案1
            int plan1 = lastBefBst + nums[idx];
            // 方案2
            int plan2 = lastBst;

            int cur = Math.max(plan1, plan2);

            // 更新 lastBefBst lastBst
            lastBefBst = lastBst;
            lastBst = cur;
        }

        return lastBst;
    }
}
