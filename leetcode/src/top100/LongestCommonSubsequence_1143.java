package top100;

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。



示例 1:

输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace"，它的长度为 3。
示例 2:

输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc"，它的长度为 3。
示例 3:

输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0。


提示:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
输入的字符串只含有小写英文字符。

分析：
1.暴力法，以 text1 的每个位置作为最长公共子序列的头，去进行匹配，匹配成功，继续往下匹配，直到 text2 或者 text1 到达尽头
统计结果值；交换 text1 text2 的位置，在进行一圈匹配，更新最大值； 时间复杂度O(N^3)
2，备忘录可优化吗 可以 mem[i][j] 记录 text1 的i 开始的子串和 text2 的 j 开始的子串的获取的长度
每当找到该位置时，先查备忘录，查不到再计算。根缓存的思想有些像。
3，

参考题解：
定义 dp[i][j] 表示 text1[0~i] 和 text[0~j] 的 lcs
初始状态 dp[0][i] = 0; dp[j][0] = 0;
状态转移 dp[i][j] = dp[i-1][j-1] + 1 ; text1[i] == text[j];
                =  max(dp[i-1][j], dp[i][j - 1]) other


 */
public class LongestCommonSubsequence_1143 {

    public int longestCommonSubsequence3(String text1, String text2) {
        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();

        int m = chs1.length;
        int n = chs2.length;

//        int[][] dp = new int[m + 1][n + 1];
        int[] dp = new int[n + 1];
        // initial

        int left = 0; // 记录的应该是左上角的值，因为等于的情况下，左上角的值会丢失
        // state transfer
        // 注意这里数组的长度发僧了变化，要注意边界
        for (int i = 1; i <= m; i ++) {
            // 没遍历一行，left 要重置开始
            left = 0;
            for (int j = 1; j <= n; j ++) {
                // 记录 左上角 防止被覆盖
                int leftTop = dp[j];
                if (chs1[i - 1] == chs2[j - 1]) dp[j] = left + 1;
                else dp[j] = Math.max(dp[j], dp[j - 1]);
                // update left
                left = leftTop;
            }
        }

        return dp[n];
    }

    // 通过填表看更新结果可知，每一行的更新其实只用到上一行的数据和左边的一个数据，所以可以对空间践行
    // 亚索
    public int longestCommonSubsequence2(String text1, String text2) {

        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();

        int m = chs1.length;
        int n = chs2.length;

        int[][] dp = new int[m + 1][n + 1];
        // initial
        for (int i = 0; i <= m; i ++) dp[i][0] = 0;
        for (int j = 0; j <= n; j ++) dp[0][j] = 0;

        // state transfer
//        for (int i = 1; i < m; i ++) {
//            for (int j = 1; j < n; j ++) {
        // 注意这里数组的长度发僧了变化，要注意边界
        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <= n; j ++) {
                // chs1 中的下标 为 i 的元素，在 dp 中的下标是 i + 1，因为 chs 是从 0 开始计数的
                if (chs1[i - 1] == chs2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];

    }


//    int ret = Integer.MIN_VALUE;
//    public int longestCommonSubsequence(String text1, String text2) {
//
//        if (text1 == null || text2 == null) return 0;
//
//        for (int i = 0; i < text1.length(); i ++) {
//            for (int j = 0; j < text2.length(); j ++) {
//                int cnt = countHuiwen(text1.substring(i), text2);
//                ret = Math.max(cnt, ret);
//            }
//        }
//
//        // 反着来一次 外层 text2 内层 text1
//        // 略
//        return 0;
//    }
//
//    private int countHuiwen(String substring, String text2) {
//        return 0;
//    }
}
