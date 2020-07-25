package top100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/*

给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
当另一个信封的宽度和高度都比这个信封大的时候，
这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

说明:
不允许旋转信封。

示例:

输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出: 3
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

分析：
先进行排序（按照第一个数字排序） 2,3   5,4   6,4  6,7
回溯 从第一个位置

问题：这离的加入是需要这里判断的，前一个的宽和高都小于后一个，不能等于

该问题可看成寻找最长子序列问题，难点是最长子序列是一维的，这里是二维的
针对该问题，我们把数组按照 第一位升序排序，第一位相等，在按第二位降序排序；
为什么是降序排序呢？因为 第一位相同时的多个信封中只能选一个，选取最大的就会排除掉其他的
对于得到的第二纬度，抽取出来求最长公共子序列即可


 */
public class MaxEnvelopes_354 {

    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;

        if (envelopes.length == 1) return 1;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    // 第二维度降序
                    return o2[1] - o1[1];
                } else {
                    // 第一维度升序
                    return o1[0] - o2[0];
                }
            }
        });

        int [] secondArr = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            // 转换得到第二位数组
            secondArr[i] = envelopes[i][1];
        }

        return lengthOfLIS2(secondArr);
    }

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

    // 求最长公共子序列
    private int lengthOfLIS(int[] secondArr) {
        // 贪心 + 二分
        int n = secondArr.length;
        int [] arr = new int[n];
        if (n == 0) return 0;
        int len = 0;
        arr[0] = secondArr[0];
        for (int i = 1; i < n; i++) {
            int cur = secondArr[i];
            if (cur > arr[len]) arr[++len] = cur;
            else {
                // 二分查找第一个小于 cur 的位置的下一个
                // 小于肯定不是我们要的位置
                int left = 0;
                int right = len;
                while (left < right) {
                    int mid = left + (right - left) / 2;
//                    if (secondArr[mid] < cur) {
                    // 这里的数组是 新建的数组，不是原来的数组
                    if (arr[mid] < cur) {
                        left = mid + 1;// 排除了 [0, mid] 新的搜索区域 [mid + 1, right]
                    } else {
                        right = mid;
                    }
                }
                // left 位置一定是我们要找的位置 最后只剩一个位置了，每次我们都排除了不是解的位置；如果要解，一定在 left,没有解，判断left 位置
                // 另外地，while 的退出条件是 left == right 所以 这里left 和 right 用那个处理都行
                arr[left] = cur;
            }
        }
        return len + 1;
    }

    int max = Integer.MIN_VALUE;
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;

        if (envelopes.length == 1) return 1;

        // 排序 按照第一个字母
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

//        System.out.println(Arrays.toString(envelopes[0]));
//        System.out.println(Arrays.toString(envelopes[1]));
//        System.out.println(Arrays.toString(envelopes[2]));
        backTrack(envelopes, 0, new LinkedList<int[]>());

        return max;
    }

    private void backTrack(int[][] envelopes, int cur, LinkedList<int[]> pre) {
//        // 剪枝
//        if (pre.size() > max) return;

        // 结果统计
        if (cur == envelopes.length) {
            max = Math.max(max, pre.size());
            //
            return;
        }

        boolean flag = false;
        for (int j = cur; j < envelopes.length; j++) {
//            if (pre.isEmpty() || isValid(envelopes[cur], pre.getLast())) {
            // 有可能某次进入这个循环里后，后面判断都不成功，此时pre 先添加 后减少了 相当于没做
            // 也就是说前面的结束条件根本就没有到达
            // 剪枝
            if (pre.size() >= max) break;
            if (pre.isEmpty() || isValid(envelopes[j], pre.getLast())) {
                pre.add(envelopes[j]);
                backTrack(envelopes, j + 1, pre);
                pre.removeLast();
            }
        }

        // 结果统计
        max = Math.max(max, pre.size());
    }

    // 判断是否有效
    private boolean isValid(int[] first, int[] next) {

        return first[1] > next[1] && first[0] > next[0];
    }

    public static void main(String[] args) {
        MaxEnvelopes_354 test = new MaxEnvelopes_354();

        int[][] arrs = {{7,8},{12,16},{12,5},{1,8},{4,19},{3,15},{4,10},{9,16}};
        System.out.println(test.maxEnvelopes(arrs));
    }
}
