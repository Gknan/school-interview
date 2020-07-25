package top100;

import java.util.LinkedList;

/*
有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:

你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
示例:

输入: [3,1,5,8]
输出: 167
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

分析：
先写个 n! 的版本
优化：
怎么剪枝？

DP 方法
状态 + 选择
存在的问题，3 1 5 8 假设选择一个位置戳破，剩下两个子问题，这两个子问题 3 1     8 有依赖关系，即先戳破 8 还是 3 1 对另外一个都有影响，因为求得分数是戳破的位置以及他的左右决定的
那么怎么去解决这个依赖关系？
而 DP 要求每个子状态之间不应该相互依赖，怎么解决？
定义开区间 dp[i, j]，dp[i, j] 表示戳破 i j 之间的所有气球得到的最大分数；
dp[i, j] = dp[i, k - 1] + dp[k + 1， j] + nums[i] * nums[k] * nums[j] 表示最后戳破气球 k 得到的结果
base case dp[i, j] = 0 i = j 时，因为 i = j + 1 时，中间是没有气球的，所以得到应该是 0
开始我们将 nums 数组扩展到 n + 2 的长度，最左边和右边添加上 1
求解矩阵移动的步骤：根据 base 的位置和 最终要求的结果的位置 dp[0][n + 1]，倒推dp 执行的步骤（即 for 循环的写法）

 */
public class MaxCoins_312 {

    int ret;
    public int maxCoins(int[] nums) {
        //
        LinkedList<Integer> numsList = new LinkedList<>();
//        numsList.add(1);
        for (int num: nums) numsList.add(num);
//        numsList.add(1);

        ret = 0;
        help(numsList, 0);

        return ret;
    }

    public int maxCoinsDP(int[] nums) {

        int n = nums.length;
        if (n == 0) return 0;
        // 构造新的 point 数组
        int[] points = new int[n + 2];
        points[0] = 1;
        points[n + 1] = 1;
        for (int i = 0; i < n; i ++) {
            points[i + 1] = nums[i];
        }

        // dp 数组
        int[][] dp = new int[n + 2][n + 2];
        // base

        //
        for (int i = n; i >= 0; i --) {
            // 下到上
            for (int j = i + 1; j <= n + 1; j ++) {
                // 左到右
//                for (int k = i + 1; k < j - 1; k ++) {
                for (int k = i + 1; k < j; k ++) { // k 的取值范围是 i + 1 ~ j - 1
//                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + points[i] * points[k] * points[j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                }
            }
        }

        return dp[0][n + 1];
    }

    private void help(LinkedList<Integer> numsList, int preSum) {
        // 边界
        if (numsList.size() == 1) {
            ret = Math.max(ret, preSum + numsList.get(0));
            return;
        }

        // 剪枝
//        if (preSum < ret)

        // 遍历所有情况，问题，LinkedList 不能边遍历边修改
        for (int i = 0; i < numsList.size(); i++) {
            LinkedList<Integer> clone = (LinkedList<Integer>) numsList.clone();
            Integer remove = numsList.remove(i);
            if (i == 0) {
                help(new LinkedList<>(numsList),
                        preSum + remove * clone.get(i + 1));
            } else if (i == clone.size() - 1) {// 变动的 list 不好处理
                help(new LinkedList<>(numsList),
                        preSum + remove * clone.get(i - 1));
            } else {
                help(new LinkedList<>(numsList),
                        preSum + remove * clone.get(i - 1) * clone.get(i + 1));
            }
//            numsList.add(i, numsList.get(i));
            numsList.add(i, remove);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
//        System.out.println(new MaxCoins_312().maxCoins(nums));
        System.out.println(new MaxCoins_312().maxCoinsDP(nums));
    }
}
