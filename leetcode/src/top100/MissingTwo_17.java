package top100;

import java.util.Arrays;

/*
给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？

以任意顺序返回这两个数字均可。

示例 1:

输入: [1]
输出: [2,3]
示例 2:

输入: [2,3]
输出: [1,4]
提示：

nums.length <= 30000

分析：
1，对数组进行排序，排序完之后遍历一遍即可。找到，时间 O(nlogn) 空间 排序 O(1) 找元素O(1)
2，通排序法，boolean [] buekt 记录是否出现过，第二遍遍历找到未出现的数字 时间 O(N) 空间 O(N)
3，若值确实一个数字，直接可以通过数学公式来计算出来
4，指针法可以用吗？
假设给的数组长度是 N 个，确实的元素用 0 标识，这样的话，我们每个位置的元素放回原来的位置，到最后位置是 0 的元素就是
结果
5，但是实际给的位置只有 N - 2 个，怎么办？
6，这里 使用 java 开发存在的问题是不能直接给后面加元素；
7，除了直接交换两个位置的元素，还有的做法是 负数 来表示该位置 没有

另外的思路：
2.数学计算， x^2 + y^2 = (1^2 + ... + N ^2) - sum(i^2); x + y = (1 + .. +Ｎ)　－　sum(i)
(1^2 +++ N^2) = N * N + 1 * (2N + 1)/6; 1+N = N(N + 1)2

3,sumOfTwo = (1 + .. +Ｎ)　－　sum(i); 两个数字不等，threadShold = sumOfTow /2
小的树 < threadShold 大的大于threadShold
遍历求得所有小于 threadshold 的数组的和 并计算1+..+threshold 的和，可以计算出 第一个
根据第一个可以计算第二个

 */
public class MissingTwo_17 {

    // 使用额外的结果的两个位置模拟 最后的位置
    public static int[] missingTwo2(int[] nums) {
        if (nums == null || nums.length < 2) return new int[0];
        if (nums.length < 3) return new int[]{1, 2};

        int[] help = new int[2]; // 分别对应的是数组的 N-2, N - 1 位置

        // 下标为 i 的位置最终放到应该是 i+1
//        for (int i = 0; i < nums.length; i ++) {
//
//            // 循环直到本位放置的是自己应该放到值
//            while (nums[i] != i + 1 && nums[i] != 0) {
//                swap(help, nums, i, nums[i] - 1);
//            }
//        }
//
//        for (int i = 0; i < help.length; i ++) {
//            while (help[i] != nums.length  + i + 1 && help[i] != 0) {
//                swap(nums, help, nums.length  + i, nums[i] - 1);
//            }
//        }

        int[] ret = new int[2];

        int idx = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) ret[idx ++] = i + 1;
        }

        for (int i = 0; i < help.length; i ++) {
            if (help[i] == 0) ret[idx ++] = nums.length + i + 1;
        }

        return ret;
    }

    // 交换 a b 位置的元素，若 a b 超过 数组长度，映射的 help 上
    private static void swap(int[] help, int[] nums, int a, int b) {

        if (b > nums.length) {
            int newIdx = b % nums.length;
            int tmp = nums[a];
            nums[a] = help[newIdx];
            help[newIdx] = tmp;
        } else if (a > nums.length) {
            int newIdx = a % nums.length;
            int tmp = help[newIdx];
            help[newIdx] = nums[b];
            nums[b] = tmp;
        } else {
            nums[a] ^= nums[b];
            nums[b] ^= nums[a];
            nums[a] ^= nums[b];
        }
    }

    // 设计给的数组长度是 N
    public static int[] missingTwo(int[] nums) {

        if (nums == null || nums.length < 2) return new int[0];
        if (nums.length < 3) return new int[]{1, 2};

        // 下标为 i 的位置最终放到应该是 i+1
        for (int i = 0; i < nums.length; i ++) {

            // 循环直到本位放置的是自己应该放到值
            while (nums[i] != i + 1 && nums[i] != 0) {
                swap(nums, i, nums[i] - 1);
            }
        }

        int[] ret = new int[2];
        int idx = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) ret[idx ++] = i + 1;
        }

        return ret;

    }

    private static void swap(int[] nums, int a, int b) {
        nums[a] ^= nums[b];
        nums[b] ^= nums[a];
        nums[a] ^= nums[b];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 0, 0};
        int[] nums2 = {2, 3};
        System.out.println(Arrays.toString(missingTwo(nums)));
        System.out.println(Arrays.toString(missingTwo2(nums2)));
    }
}
