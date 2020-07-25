package top100;

import java.util.Arrays;

/*
给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums
 中除 nums[i] 之外其余各元素的乘积。

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

分析：
1，暴力法，对于每个元素，计算除去它之外的所有元素乘积 时间复杂度O(N^2)
2，开辟二维数组 help，help[i, j] 表示 从 i 位置累乘到 j 位置的积

方法一：
两个数组 leftMulti[] rightMulti[] 分别表示从最左边累乘到当前位置之前的积 和 从最右边累乘到当前位置之前的积
ans[i] = leftMulti[i] * rightMulti[i]
第一个遍从到右遍历填 leftMulti 表； 第二遍从右到左遍历填 rightMulti 表
空间 O(N) 时间 O(N)

方法二：
双指针法
利用两个变量 p q 分别表示到当前位置时 前面的累乘积
第一遍利用 p 更新 ans
第二遍利用 q 更新 ans
时间复杂度 O(n)
空间复杂度 O(1)

 */
public class ProductExceptSelf_238 {

    // 利用两个指针完成
    public static int[] productExceptSelf2(int[] nums) {
        int n = nums.length;

        int p = 1;// from left to right
        int q = 1; // from right to left
        int[] ans = new int[n];

        for (int i = 0; i < n; i ++) {
            ans[i] = p;
            p *= nums[i];
        }

        for (int i = n - 1; i >= 0; i --) {
            // update ans
            ans[i] = ans[i] * q;
            // update q
            q *= nums[i];
        }

        return ans;
    }


    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] leftMulti = new int[n];
        int[] rightMulti = new int[n];

        int[] ans = new int[n];

        // from left to right
        leftMulti[0] = 1;
        for (int i = 1; i < n; i++) {
            leftMulti[i] = leftMulti[i - 1] * nums[i - 1];
        }

        // from right to left
        rightMulti[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
//            rightMulti[i] = rightMulti[i - 1] * nums[i - 1];
            rightMulti[i] = rightMulti[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = leftMulti[i] * rightMulti[i];
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }
}
