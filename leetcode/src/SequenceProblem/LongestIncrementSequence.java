package SequenceProblem;

/*
求最长上升子序列问题

dp
dp[i] 以 nums[i] 结尾的当前序列中的最长递增子序列的个数
dp[i] = Math.max(dp[0],...dp[i-1]) + 1

贪心 + 二分查找：
自始至终维护一个 每个位置都是最小的最长递增子序列 minLongSub  维护对应的 len 表示当前的指向的位置
若 当前 cur > minLongSub[len] 当前元素加到 minLongSub 之中
否则，二分查找 minLongSub 找到第一个小于小于 cur 的位置 i，替换掉 minLongSub[i + 1]
 */
public class LongestIncrementSequence {

    // 每次遍历 位置 i 之前的所有 位置，找到最长的递增子序列
    // 要相求最长子序列的具体，可以使用另外一个数组记录 i 的前一个字符的下标
    public int lengthOfLIS2(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 2) return nums.length;

        int n = nums.length;
        int[] dp = new int[n];
        int ret = 0;

        // 第 0 个位子的最长上升子序列就是自己 所以为 1
        dp[0] = 1;

        // 计算并填写 dp 表；每个位置应该遍历找完 i 之前虽有的位置
        for (int i = 1; i < n; i++) {
            int max = 0;
            // 先找到前面部分的最大值，再更新
            for (int j = 0; j < i; j++) {
//                max = (nums[i] > nums[j]) ? nums[j] + 1 :dp nums[j];
                if (nums[i] > nums[j]) {
//                    max = Math.max(max, nums[j] + 1);
                    max = Math.max(max, dp[j]);
                }
            }
            // 遍历找到前面的部分的最长递增子序列，然后再新
            dp[i] = max + 1;
            ret = Math.max(dp[i], ret);
        }
        return ret;
    }


    /*
    贪心 + 二分查找：
    自始至终维护一个 每个位置都是最小的最长递增子序列 minLongSub  维护对应的 len 表示当前的指向的位置
    若 当前 cur > minLongSub[len] 当前元素加到 minLongSub 之中
    否则，二分查找 minLongSub 找到第一个小于小于 cur 的位置 i，替换掉 minLongSub[i + 1]
    */
    public static int lengthOfLIS3(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;

        // 维护的借助 cur 位置找到的最长递增子序列 长度最长为 n
        int[] minLongSub = new int[n];

        // minLongSub 中放的是 nums[i] 的具体指
        int len = 0; // 当前应该填充的位置 当前遍历的位置对应的会对 minLongSub 最后一个位置
        for (int i = 0; i < n; i++) {
            // 比较 cur 和 minLongSub[len] 插入元素
            int cur = nums[i];

            // 二分查找 0 ~ len [0, len)
            // 考虑不是解的方法来写 二分查找  我们要找的是第一个小于 cur 的位置的下一个位置
            // 如果 nums[mid] 严格小于目标元素，它一定不是我们要找到元素 等于有可能是解；所以等于被划分到下一步要搜索的空间
            // 小于一定不是解，排除 [0, mid] 下一个搜索的空间是 [mid +1, last]
            // 当前 cur < mid 时，一定不是解
            int start = 0;
            int end = len;
            // 采用排除法缩减空间
            while (start < end) { // 退出循环时，一定有 start == end 我们只需要 start 或者 end 做处理
                int mid = (start + end) / 2;
                if (minLongSub[mid] < cur) { // 先写一定不是解的空间判断，排除掉该空间
                    // 此时 [0，mid] 都被排除了
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            // 这样的好处是最终 start == end ，我们不用去区分解在 start 还是在 end 中

            if (start == len) len++;
            minLongSub[start] = cur;// left 是最左边界
        }

        // 求的是最长子序列的长度，所以这里是 len，也就是最后一元素的下一个位置
        return len;
    }

}
