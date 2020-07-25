package top100;

/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

分析：
1，因为给你的数字是没有范围的，所以无法使用桶。
2，暴力法，以每个位置作为开头，向后找最长递增子序列，
3，动态规划法，加入说我们找 i 开头的最长递增子序列，必须要知道 0~i-1 的最长递增子序列
，并且还需要知道 0~i-1 的最长递增子序列的最大元素是多少；
若 lastMost >= i; 截止 i 的最长递增子序列就是等于 i-1 结尾的子序列，且保持 lastMost 不变
若 lastMost < i;则 + 1，更新 lastMost

定义 dp[i][j] 0<=i<n 0<=j<2  dp[i][0] 表示已 i 作为结尾的最长递增子序列长度； dp[i][1] 表示 最长递子序列的最后一个元素

init dp[0][0] = 1; dp[0][1] = nums[0]

状态转移公式：
dp[i][0] = dp[i - 1][0]  dp[i][1] = dp[i-1][1] nums[i] <= dp[i-1][1]
dp[i][0] = dp[i -1][0] + 1 dp[i][1] = nums[i]; nums[i] > dp[i - 1][1]
上面的状态定义有问题
因为确定 第 i 位是否作为最长公工子序列的尾巴是，我们需要比较前面的最长公共子序列，二这些信息很难保存。
如果采取 dp[i] 标识 以 i 为元素作为最长公共子序列的尾巴的长度，那么我们可以遍历 0~i 来确定 dp[i] 的值
最终的这结果就是 dp[i] 中的最大值;也就是说 nums[i] 在 dp[i] 决定时必选

怎么写状态转移方程呢？遍历并计算

dp[i] 以 nums[i] 结尾的当前序列中的最长递增子序列的个数
dp[i] = Math.max(dp[0],...dp[i-1]) + 1

贪心 + 二分查找：
自始至终维护一个 每个位置都是最小的最长递增子序列 minLongSub  维护对应的 len 表示当前的指向的位置
若 当前 cur > minLongSub[len] 当前元素加到 minLongSub 之中
否则，二分查找 minLongSub 找到第一个小于小于 cur 的位置 i，替换掉 minLongSub[i + 1]

 */
public class LengthOfLIS_300 {

    public static int lengthOfLIS3(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;

        int[] minLongSub = new int[n];

        // minLongSub 中放的是 nums[i] 的具体指
        int len = 0; // 当前应该填充的位置
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
            while (start < end) { // 退出循环时，一定有 start == end 我们只需要 start 或者 end 做处理
                int mid = (start + end) / 2;
                if (minLongSub[mid] < cur) { // 先写一定不是解的空间判断，排除掉该空间
                    // 此时 [0，mid] 都被排除了
                    start = mid + 1;
                } else {
                    end = mid;
                }
//                if (minLongSub[mid] == cur) {
//                    end = mid;
//                } else if (minLongSub[mid] < cur) {
//                    start = mid + 1;
//                } else if (minLongSub[mid] > cur) {
//                    end = mid;
//                }
            }

            if (start == len) len++;
            minLongSub[start] = cur;// left 是最左边界
        }

        return len;
    }

    public static int lengthOfLIS4(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
        int[] tail = new int[len];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail 中
                // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                    // int mid = left + (right - left) / 2;
                    int mid = left + ((right - left) >>> 1);
                    if (tail[mid] < nums[i]) {
                        // 中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                // 因此，无需再单独判断
                tail[left] = nums[i];
            }
            // 调试方法
            // printArray(nums[i], tail);
        }
        // 此时 end 是有序数组 tail 最后一个元素的索引
        // 题目要求返回的是长度，因此 +1 后返回
        end++;
        return end;
    }

    // 调试方法，以观察是否运行正确
    private void printArray(int num, int[] tail) {
        System.out.print("当前数字：" + num);
        System.out.print("\t当前 tail 数组：");
        int len = tail.length;
        for (int i = 0; i < len; i++) {
            if (tail[i] == 0) {
                break;
            }
            System.out.print(tail[i] + ", ");
        }
        System.out.println();
    }

    //
    public int lengthOfLIS2(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 2) return nums.length;

        int n = nums.length;
        int[] dp = new int[n];
        int ret = 0;

        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            // 先找到前面部分的最大值，再更新
            for (int j = 0; j < i; j++) {
//                max = (nums[i] > nums[j]) ? nums[j] + 1 :dp nums[j];
                if (nums[i] > nums[j]) {
//                    max = Math.max(max, nums[j] + 1);
                    max = Math.max(max, dp[j]);
                }
//                dp[i] = max;
//                ret = Math.max(dp[i], ret);
            }
            // 遍历找到前面的部分的最长递增子序列，然后再新
            dp[i] = max + 1;
            ret = Math.max(dp[i], ret);
        }


        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {10, 10};
        System.out.println(lengthOfLIS3(arr));
    }

//    public int lengthOfLIS(int[] nums) {
//
//        if (nums == null) return 0;
//        if (nums.length < 2) return nums.length;
//
//        int n = nums.length;
//        int[][] dp = new int[n][2];
//
//        dp[0][0] = 1;
//        dp[0][1] = 0
//
//        for (int i = 1; i < n; i ++) {
//
//        }
//
//    }
}
