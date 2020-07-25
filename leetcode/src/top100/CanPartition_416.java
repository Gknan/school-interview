package top100;

import java.util.Arrays;
import java.util.Collections;

/*
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:

每个数组中的元素不会超过 100
数组的大小不会超过 200
示例 1:

输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
 

示例 2:

输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.

分析：
统计数组的和 num，若num 是奇数，返回false；否则，在数组中查找是否存在和为 num / 2 的子数组
对数组排序后，查找 和 为 num / 2 的子数组

 */
public class CanPartition_416 {

    public boolean canPartition(int[] nums) {

        if (nums.length < 2) return false;

        int sum = 0;
        for (int num: nums) sum += num;

        if (sum % 2 == 1) return false;

        return exist(nums, sum / 2);
    }

    /* nums 中是否存在和为 sum 的子数组 */
    private boolean exist(int[] nums, int sum) {

        Arrays.sort(nums);

        return help(nums, sum, 0);
    }

    private boolean help(int[] nums, int sum, int start) {
//        if (sum == 0) return true;
        if (start == nums.length) return false;

        for (int i = start; i < nums.length; i++) {
            // 剪枝 主要是由于数组有序
            if (sum - nums[i] < 0) break;
            if (sum - nums[i] == 0) return true;

            // 第二个剪枝，如果是相同的元素，那么前面的若找不到解，可以直接跳过
//            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 有一个坑。。。。。。。。 每次应该是比较当前 help 范围内的前一个元素
            // 主要还是画图，然后看对应的转换关系，还有一点要注意，这类问题的重复例子考虑，以方便优化剪枝
            if (i > start && nums[i] == nums[i - 1]) continue;

//            if (help(nums, sum - nums[i], start + 1)) return true;
            if (help(nums, sum - nums[i], i + 1)) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {100,100,100,100,100,100,100,100};

        System.out.println(new CanPartition_416().canPartition(nums));
    }
}
