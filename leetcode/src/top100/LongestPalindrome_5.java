package top100;

import java.util.ArrayList;

/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

分析：
1，暴力法，遍历所有的两个位置的组合，检测是否是回文串，同时记录最长长度
2，Machelar 算法，
raduis[] 数组，记录每个位置的半径
R ： 记录当前扎熬到的最右边界
C：最右边界对应的位置
L：最有边界关于 R 的对称 L= 2 * C - R
i: 当前处理的位置，以i 为中心来找回文串
li：i 关于 C 的对称点 li = 2 * C - i
lp：li 最为中心的回文串的左边界

为了避免奇偶的问题，我们为字符串的所有位置加上 #，前后都加，返回的最长半径就是解

几个注意点：
1，R 的初始位置是 -1，R 的含义是 包含 R 位置在的字符是右边界。
2，Rad 的值至少是1，一个字母也是回文串，也是有大小的
3，若 R >= i 那么 R 与 i 的之间的距离实际上是用 R - i + 1 来算的；更新 R 是，i + rad[i] - 1
4，while 时，注意， i + rad[i] 和 i - rad[i] 是我们要比较的位置 rad[i] 是动态更新的
5，new String(char[], offset, count) String.toSubString(start, end + 1);

 */
public class LongestPalindrome_5 {

    // 中心扩展法
    public static String longestPalindrome2(String s) {
        if (s == null || s.trim().equalsIgnoreCase("") || s.length() < 2) return s;

//        int idx = 0
        int start = 0, end = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            // 以每个字符为中心向两边扩展

            // 最长产生是计数
            int len1 = expand(chs, i, i);
            // 最长串是偶数
            int len2 = expand(chs, i, i + 1);

            int len = Math.max(len1, len2);
//            if (len > max) {
//                idx = i;
//            }
            if (len > end - start) {
                if (len == len1) {
                    // update start
                    start = i - len1/2;
                    end = i + len1/2;
                } else {
                    start = i - (len/2 - 1);
                    end = i + len/2;
                }
            }

        }

//        return new String(chs, idx, max);
        return s.substring(start, end + 1);

    }

    // 从中心向外扩展，统计最长回文距离
    private static int expand(char[] chs, int i, int i1) {
        int ret = i == i1 ? -1 : 0;
        while (i >= 0 && i1 < chs.length && chs[i --] == chs[i1 ++]) {
            ret += 2;

            // upate while
        }

        return ret;
    }


    public static String longestPalindrome(String s) {
        if (s == null || s.trim().equalsIgnoreCase("") || s.length() < 2) return s;

        char[] ss = s.toCharArray();
        char[] sTran = transAdd(ss).toCharArray();

        String ret = mancherSolve(sTran);
        return transRemove(ret);
    }

    // 去掉所有 #
    private static String transRemove(String ret) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ret.length(); i++) {
            if (ret.charAt(i) != '#') {
                stringBuilder.append(ret.charAt(i));
            }
        }

        return stringBuilder.toString();


    }

    private static String mancherSolve(char[] s) {

        int C = -1, i = 0, R = -1;
        int[] rad = new int[s.length];

        int maxLen = Integer.MIN_VALUE;
        int maxIdx = -1;

        for (;i < s.length; i++) {
            // rad[i] = i > R ? 1 : Math.min(R - i, rad[2 * C - i]);
            rad[i] = i > R ? 1 : Math.min(R - i + 1, rad[2 * C - i]);

//            while (rad[i] + 1 < s.length && rad[i] - 1 >= 0
//                    && s[rad[i] + 1] == s[rad[i] - 1]) {
            while (i + rad[i] < s.length && i - rad[i] >= 0
                    && s[i + rad[i]] == s[i - rad[i]]) {
                rad[i] ++;
            }

            // update C R
            if (rad[i] > R) {
                C = i;
//                R = rad[i];
                R = i + rad[i] - 1; // R 始终是最右边界本位
            }

            // update idx
            if (rad[i] > maxLen) {
                maxLen = rad[i];
                maxIdx = i;
            }
        }

        return new String(s, maxIdx - (maxLen - 1), 2 * maxLen - 1);
    }

    private static String transAdd(char[] ss) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            sb.append("#" + ss[i]);
        }

        sb.append("#");

        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "babad";
        System.out.println(longestPalindrome(str));

        System.out.println(longestPalindrome2(str));


    }
}
