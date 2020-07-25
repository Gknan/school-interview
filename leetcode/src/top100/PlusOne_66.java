package top100;

import java.util.Arrays;

/*
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:

输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
示例 2:

输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。

分析：
可能存在进位，导致数组长度发生变化；
方案1，原来的数组求值，求到后 +1, 将结过写回数组
但是方案一很可能会导致溢出
还是在原来的数组上直接操作，若发生进位，新建数组并移动

 */
public class PlusOne_66 {

    public int[] plusOne(int[] digits) {

        if (digits.length < 1) return new int[0];

        int n = digits.length;
        int up = 0;
        for (int i = n - 1; i >= 0; i--) {
            // 最后一个位置当前为的值为 当前为 + 进位 + 1
            int val = digits[i] + up;
            if (i == n - 1) val += 1;

            // 根据 val 更新 up
            up = val / 10;
            val %= 10;
            // 更新当前为
            digits[i] = val;
        }

        if (up != 0) {
            // 假发进位导致整数范围加大，新建数组，长度加1后赋值
            int [] newRet = new int[n + 1];
            int k = n;
            for (int i = n - 1; i >= 0; i--) {
                newRet[k --] = digits[i];
            }

            // 第一个位置赋值
            newRet[k] = up;
            return newRet;
        }

        return digits;
    }

    public static void main(String[] args) {
        PlusOne_66 test = new PlusOne_66();

        int[] digits = {9,9,9,9};
        System.out.println(Arrays.toString(test.plusOne(digits)));
    }
}
