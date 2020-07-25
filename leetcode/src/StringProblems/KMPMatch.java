package StringProblems;

/*
实现 strStr() 函数。
给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个
位置 (从0开始)。如果不存在，则返回  -1。
示例 1:
输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:
输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

分析：
典型的字符串匹配问题，使用 KMP 算法解决
 */
public class KMPMatch {

    public int strStr(String haystack, String needle) {

        // 题目中明确表示 needle 为空时返回为0
        if (needle.length() == 0) return 0;

        char[] text = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        // 根据 needle 构建匹配的 b 数组
        int[] b = new int[needle.length() + 1];
        int i = 0, j = -1;
        b[i] = j;
        while (i < needle.length()) {
            // 若 j 指针为 -1， 直接更新 i j ；若 j 指针>=0 比较 needle 当前 i 位置和 已完成匹配的 j 位置是否相等，
            // 不相等，j 指向 b[j] 这里更新的逻辑是 假设当前位置不匹配了，pattern 怎么找合适的匹配位置
            while (j >= 0 && pattern[i] != pattern[j]) j = b[j];

            // 走到这里说明要么是 j = -1 ；要么是 当前的 pattern[i] == pattern[j] 当前 i 匹配成功，就会告诉 i+1，让他不匹配时先去找 b[j + 1]
            i++; j++;
            b[i] = j;
        }
        // 循环退出条件，i== n-1时，已经更新了 b[i+1]
        // 借助 b 数组完成字符串匹配
        i = 0;
        j = 0;
        while (i < text.length) {
            // 开始匹配字符串
            while (j >= 0 && text[i] != pattern[j]) {
                // pattern 不匹配，根据 b 相前找位置 找到 匹配或者 j = -1,说明没有匹配的位置
                j = b[j];
            }
            i ++;
            j ++;
            if (j == pattern.length) {
                // 说明 pattern已经被匹配完成 返回
                return i - pattern.length;
            }
        }
        return -1;
    }
}
