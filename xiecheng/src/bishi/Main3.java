package bishi;

import java.util.Scanner;

/*
ElasticSearch 是常用的开源搜索引擎，支持fuzzyQuery 给搜索带来很大便利。

其简单原理如下，surprize有拼写错误，把z换成s后得到  surprise，即纠正一个字母，

就可以匹配正确的单词。

同样，把surprize的z替换成s，然后在末尾加个d，可以得到surprised。


给定字典[ "surprise", "happy", "ctrip", "travel", "wellcome","student","system","program","editor"]

为正确单词。


编程实现单词纠正，判断输入的单词能否在2(包含)次纠正操作内得到字典中的单词。

纠正操作是以下三种，

1：替换字符串中的一个字母;

2：删除字符串中的一个字母;

3：在字符串中增加一个字母。


待纠正的单词1

...

待纠正的单词n

输出
如果可以匹配,请返回字典中的单词,

如果无法匹配,请返回字符串null


样例输入
hipp
样例输出
happy
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dic = {"surprise", "happy", "ctrip", "travel", "wellcome", "student", "system", "program", "editor"};


        while (scanner.hasNext()) {
            String input = scanner.next();
//        String input = "hipp";
            boolean flag = false;
            for (String string : dic) {
                int midDis = minDistance(input, string);
                if (midDis <= 2) {
                    System.out.println(string);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("null");
            }
        }
    }

    // DP 法
    public static int minDistance(String word1, String word2) {
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();

        int m = chs1.length;
        int n = chs2.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 多加一个元素要注意遍历下标和 DP 数组下标的对应关系
//                if (chs1[i] == chs2[i]) dp[i][j] = dp[i - 1][j - 1];
                if (chs1[i - 1] == chs2[j - 1]) dp[i][j] = dp[i - 1][j - 1];

                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
            }
        }

        return dp[m][n];
    }
}
