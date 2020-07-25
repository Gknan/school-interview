package SequenceProblem;

/*
定义 dp[i][j] 表示 text1[0~i] 和 text[0~j] 的 lcs
初始状态 dp[0][i] = 0; dp[j][0] = 0;
状态转移 dp[i][j] = dp[i-1][j-1] + 1 ; text1[i] == text[j];
                =  max(dp[i-1][j], dp[i][j - 1]) other

 */

public class LongestCommonSubsequence {

    // 通过填表看更新结果可知，每一行的更新其实只用到上一行的数据和左边的一个数据，所以可以对空间压缩
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
        // 注意这里数组的长度发生了变化，要注意边界
        // 这里还要注意 我们之所以多加dp 多加以为，是因为 初始值是那其中一个是 空串去匹配的，若其中一个是空串，那么他们的最长公共子序列的长度就是 0
        // 为了表示空串这种情况，我们在行和列都加一个位置
        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <= n; j ++) {
                // (i,j) 位置的元素只跟 dp 的 (i -1, j- 1) 和 (i-1, j) 以及 (i, j -1) 相关，即只跟
                // 左边和上边相关，所以可以使用一维数组来进行空间压缩
                if (chs1[i - 1] == chs2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    public int longestCommonSubsequence3(String text1, String text2) {
        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();

        int m = chs1.length;
        int n = chs2.length;

        int[] dp = new int[n + 1];
        // initial

        int left = 0; // 记录的应该是左上角的值，因为等于的情况下，左上角的值会丢失
        // state transfer
        // 注意这里数组的长度发僧了变化，要注意边界
        for (int i = 1; i <= m; i ++) {
            // 每遍历一行，left 要重置开始
            left = 0;
            for (int j = 1; j <= n; j ++) {
                // 记录 左上角 防止被覆盖
                int leftTop = dp[j];
                if (chs1[i - 1] == chs2[j - 1]) dp[j] = left + 1;
                else dp[j] = Math.max(dp[j], dp[j - 1]); // 这里会导致左边的数据丢失，对于下一次更新，leftTop 和 left 空占了一个位置，
                // 更新 left 时，把leftTop 也给更新了，所以在更新 left 之前，应该下取出left 的值， 这时的追就是 leftTop 的信息
                // update left
                left = leftTop;
            }
        }

        return dp[n];
    }
}
