package top100;

import java.util.Arrays;

/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

分析：
经过分析
123  132  213  231  312 312
对于 123，倒数第二位比倒数第一位小，直接交换倒数第一和第二；若倒数第二第倒数第一大，
往前找，直到找到一个比后一个元素小的元素，然后i+1 后面所有的部分排序，交换i 第后面第一个大于i位置元素的位置
若找到头，排序后直接输出

 */
public class NextPermutation_31 {

    public void nextPermutation(int[] nums) {

        int n;
        if ((n = nums.length) < 2) return;

        int post = n - 1;
        int cur = n - 2;
        if (nums[cur] < nums[post]) {
            swap(nums, cur, post);
            return;
        } else {
            while (cur >= 0 && nums[cur] >= nums[post]) {
                post = cur;
                cur = cur - 1;
            }
            if (cur == -1) {
                // 数组是逆序的
                reverse(nums, 0, n - 1);
                return;
            }
            // cur < post post 级以后是降序的
            // post 及以后先排序
            reverse(nums, post, n - 1);
            // post 之后的位置中找到里 cur 最近且大于 cur 的位置交换，返回即可
            int idx = find(nums, post, n - 1, nums[cur]);
            swap(nums, cur, idx);
        }
    }

    /**
     * 有序数组 start ~ end 之间找 第一个严格大于 target 的位置
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int find(int[] nums, int start, int end, int target) {

        int l = start, r = end + 1;
        while (l < r) {
            int mid = l + ((r - l) / 2);
            // 排除法做做
            if (nums[mid] == target) {
                // 排除该位置，一定不是解
                l = mid + 1;
            } else if (nums[mid] > target) {
                // mid 位置可能是解
                r = mid;
            } else if (nums[mid] < target) {
                // mid 位置一定不是解
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 逆转数组
     * @param nums
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start ++, end --);
        }
    }

    private void swap(int[] nums, int cur, int post) {
        int temp = nums[cur];
        nums[cur] = nums[post];
        nums[post] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5,1,1,1,1};

        new NextPermutation_31().nextPermutation(nums);

        System.out.println(Arrays.toString(nums));
    }
}
