package top100;

/*
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:

输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
示例 2:

输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。

分析：

dp[i] 表示从 i 下标能到达结尾，值为 1 标识能，0 标识不能
求 dp[0]
init dp[n - 1] = 1
dp[i] = Math.max(dp[i,i+1,...i+dp[i]])
时间复杂度 O(n2)

优化：
dp[i] 表示 i 位置一定能到达终点，又因为 每个位置的跳跃步数是0，或者正整数，那么如果该位置是0，
dp[i][j] 表示 第i 个位置能够到达地j 个位置 i + nums[i] <= j 则一定可以到达，时间复杂度也是 O(n2)

贪心解法：
对于位置 y ，若 存在 x，x+nums[x] 的值大于等于 y，那么y的位置就一定可以跳到
遍历，记录当前找到的最右边界，若最右边界超过了最后一个位置，那么，说明最后一个位置可达

[2,3,1,1,4]
idx = 0，最右边界 0+2= 2；
idx = 1,1 < 2，1 可达，更新最右边界 1 + 3 = 4; 4== 最后的索引位置，最后位置可达，返回


 */
public class CanJump_55 {

    public boolean canJump2(int[] nums) {
        int n;
        if ((n = nums.length) < 2) return true;

        int rB = 0;
        for (int i = 0; i < n; i++) {
            if (i > rB) return false;
            rB = Math.max(rB, nums[i] + i);
            if (rB >= n - 1) return true;
        }

        return false;
    }

    public boolean canJump(int[] nums) {

        int n;
        if ((n = nums.length) < 2) return true;

        int[] dp = new int[n];

        // init
        dp[n - 1] = 1;

        for (int idx = n - 2; idx >= 0; idx --) {
            // for cur position idx
            int len = nums[idx];
            int i = 1;
            while (len -- > 0 && idx + i < n) {
                if (dp[idx + i] == 1) {
                    // find answer
                    dp[idx] = 1;
                    break;
                }
                i ++;
            }
        }

        return dp[0] == 1;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};

        System.out.println(new CanJump_55().canJump(nums));
    }
}
