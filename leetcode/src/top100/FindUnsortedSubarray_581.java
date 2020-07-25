package top100;

/*
给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。

示例 1:

输入: [2, 6, 4, 8, 10, 9, 15]
输出: 5
解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。

说明 :

输入的数组长度范围在 [1, 10,000]。
输入的数组可能包含重复元素 ，所以升序的意思是<=。

分析：
由于只有一个连续的子数组，所以只要找到找个子数组的两边坐标　left right
从左边开始找，找到第一个不满足 nums[i] <= {nums[i+1], nums[i+2],....} 对应的下标识left
同理，从右边开始找，找到第一个不满足 nums[j] >= {nums[j -1],....}
极端情况下，数组有序，时间 O(N2)

同时从前往后和从后往前遍历，分别得到排序数组的右边界和左边界：
寻找右边界：从前往后遍历的过程中，用 max 记录遍历过的最大值，如果 max 大于当前的
nums[i]，说明 nums[i] 的位置不正确，应该属于需要排序的数组，以为将右边界更新为 i，
否则，更新 max，这样最终可以找到需要排序的右边界，右边界之后的元素都是大于 max 的，是有序的

 */
public class FindUnsortedSubarray_581 {

    public static int findUnsortedSubarray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int right = 0, left = nums.length - 1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                // nums[i] < max 说明当前的 i 的位置不正确
                right = i;
            }
        }

        for (int j = nums.length - 1; j >= 0; j --) {
            if (nums[j] <= min) {
                min = nums[j];
            } else {
                left = j;
            }
        }

        return Math.max(0, right - left + 1);
    }

    public static int findUnsortedSubarray2(int[] nums) {

        if (nums.length <= 1) {
            return 0;
        }

        int len = nums.length;

        int left = findLeft(nums);
        int right = findRight(nums);

//System.out.println("left: " + left + ", right: " + right);

        return Math.max(0, right - left + 1);

    }

    private static int findLeft(int[] nums) {
        int left = 0;
        for (; left < nums.length; left ++) {
            for (int j = left + 1; j < nums.length; j ++) {
                if (nums[left] > nums[j]) {
                    return left;
                }
            }
        }

        return left;
    }

    private static int findRight(int[] nums) {
        int right = nums.length - 1;
        for (; right >= 0; right --) {
            for (int k = right - 1; k >= 0; k --) {
                if (nums[right] < nums[k]) {
                    return right;
                }
            }
        }

        return right;
    }

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(nums));
    }
}
