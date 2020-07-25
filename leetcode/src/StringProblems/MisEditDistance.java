package StringProblems;

/*
最小编辑距离问题
给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符
示例 1:

输入: word1 = "horse", word2 = "ros"
输出: 3
解释:
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2:
输入: word1 = "intention", word2 = "execution"
输出: 5
解释:
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

分析：
1，
  h   o   r   s   e
  r   o   s
  从后向前，两个指针i j
  h o r s e
      r o s
  h o r s e s  插入 i j - 1
  h o r s s    替换  两个指针都向前移动
  h o r s      删除   i - 1   j 不变
 */
public class MisEditDistance {

    public int minDistance(String word1, String word2) {
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();

        // 递归的思想，从后往前
        return process(chs1, chs2, chs1.length - 1, chs2.length - 1);
    }

    // i j 从后往前匹配 返回最少修改的次数
    // 递归做法
    private int process(char[] chs1, char[] chs2, int i, int j) {
        // 若其中一个走到头，最少修改书就是将剩下的所有都删除
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (chs1[i] == chs2[j]) {
            return process(chs1, chs2, -- i, -- j); // 当前位置字符相同，都向前移动
        } else { // 不相同，我们三种操作都要试，去修改次数最少的即可
            // 替换元素
            int l1 = process(chs1, chs2, i - 1, j - 1) + 1;
            // 删除元素
            int l2 = process(chs1, chs2, i - 1, j) + 1;
            // 插入元素
            int l3 = process(chs1, chs2, i, j - 1) + 1;
            return Math.min(l1, Math.min(l2, l3));
        }
    }


    int [][] mem;
    // 备忘录
    public int minDistance2(String word1, String word2) {
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();

        mem = new int[chs1.length + 1][chs2.length + 1];

        return process2(chs1, chs2, chs1.length - 1, chs2.length - 1);
    }

    private int process2(char[] chs1, char[] chs2, int i, int j) {
        if (mem[i + 1][j + 1] != 0) return mem[i + 1][j + 1];
        // 若其中一个走到头，最少修改书就是将剩下的所有都删除
        if (i == -1) {
            mem[i + 1][j + 1] = j + 1;
            return j + 1;
        }
        if (j == -1) {
            mem[i + 1][j + 1] = i + 1;
            return i + 1;
        }

        if (chs1[i] == chs2[j]) {
            mem[i + 1][j + 1] =  process2(chs1, chs2, -- i, -- j);
        } else {
            // 替换元素
            int l1 = process2(chs1, chs2, i - 1, j - 1) + 1;
            // 删除元素
            int l2 = process2(chs1, chs2, i - 1, j) + 1;
            // 替换元素
            int l3 = process2(chs1, chs2, i, j - 1) + 1;
            mem[i + 1][j + 1] = Math.min(l1, Math.min(l2, l3));
        }
        return mem[i + 1][j + 1];
    }

    // DP 法
    // dp[i][j] word1 的前i 个字符转为 word2 的前 j 个字符最少编辑次数
    public int minDistance3(String word1, String word2) {
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();

        int m = chs1.length;
        int n = chs2.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i ++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j ++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <= n; j ++) {
                // 多加一个元素要注意遍历下标和 DP 数组下标的对应关系
//                if (chs1[i] == chs2[i]) dp[i][j] = dp[i - 1][j - 1];
                if (chs1[i - 1] == chs2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
            }
        }

        return dp[m][n];
    }
}
