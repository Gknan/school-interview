package top100;

/*
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

分析：
1，字符串乘法，使用 数组接收每个位置的结果
2，规律 nums1 中 i 位 和  nums2 中 j 位的结果再  ans  的 i+j i+j+ 1 位
3，
 */
public class Multiply_43 {

    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        int[] ans = new int[m + n];
        char[] nums1 = num1.toCharArray();
        char[] nums2 = num2.toCharArray();

        for (int i = m - 1; i >= 0; i --) {
            for (int j = n - 1; j >= 0; j --) {
                int first = Integer.parseInt(String.valueOf(nums1[i]));
                int second = Integer.parseInt(String.valueOf(nums2[j]));

                int ret = first * second;
                // 放到数组中
                int p = i + j, q = i + j + 1;

                ret += ans[q];

                ans[q] = ret % 10;
                ans[p] += ret / 10;

//                ans[q] += ret % 10;
//                ans[p] += ret / 10 + ans[q] / 10; // 可能有进位
//                ans[q] %= 10; // 消除进位
//                ans[p - 1] += ans[p] / 10;
//                ans[p] %= 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 返回字符串
        int i = 0;
        for (; i < ans.length; i++) {
            if (ans[i] != 0) break;// 找到第一个不为0 的位置
        }
        for (int j = i; j < ans.length; j++) {
            sb.append(ans[j]);
        }

        // 若最后结果是空串，返回 0
        return sb.toString().length() == 0 ? "0" : sb.toString();
    }
}
