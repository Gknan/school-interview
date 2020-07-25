package top100;

import java.util.Arrays;

/*
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？

分析：
1，不考虑题目意思，普通排序的思路 插入 冒泡 选择  O(N^2)  快排 归并 堆排序 O(nlogn)
2，分析题目意思，怎么运用题目，只有三种数字，所以可以使用计数排序，像题目中描述的那样
3，一趟扫描，阐述空间的算法，首先，直接更改 nums 也是常数空间，即 nums 数组可以用
只能使用指针法，尝试双指针的方法，快排的思想，直接使用 1 作为 pivot，将数组分成左 中 右三部分，正好满足题意
先写原来的快排，然后改

 */
public class SortColors_75 {

    public static void sortColors(int[] nums) {

        int n = nums.length;
        int pivot = 1;

        int p = 0; // 小于 pivot 的部分的下一个位置
        int q = n - 1; // 大于 pivot 的第一个元素的前一个位置
        int i = 0; // 迭代的下标
//        while (i < q) {
        while (i <= q) { // 由于q 的定义的修改，所以 q 位置只得元素没有处理，必须经过 i 的遍历处理
            if (nums[i] < pivot) {
                // 放到 p 位置，p 移动，i 移动
                swap(nums, p ++, i ++);
            } else if (nums[i] > pivot) {
                // 放到q 位置，q 移动
                swap(nums, q --, i);
            } else {
                // nums[i] = pivot
                i ++;
            }
        }

System.out.println(Arrays.toString(nums));
    }

    public static void partition(int[] nums) {
        int n = nums.length;
        int pivot = nums[n - 1];

        int p = 0; // 小于 pivot 的部分的下一个位置
        int q = n - 1; // 大于 pivot 的第一个元素
        int i = 0; // 迭代的下标
        while (i < q) {
            if (nums[i] < pivot) {
                // 放到 p 位置，p 移动，i 移动
                swap(nums, p ++, i ++);
            } else if (nums[i] > pivot) {
                // 交换 i 和 --q
                swap(nums, -- q, i);
            } else {
                // nums[i] = pivot
                i ++;
            }
        }

        swap(nums, n - 1, i);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2,0,1};
        sortColors(nums);
    }
}
