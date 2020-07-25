package top100;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

例如，上图是一个7 x 3 的网格。有多少可能的路径？

 

示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28
 

提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 10 ^ 9

分析：
每次移动只能向下或者向右
DFS + 回溯法，到达目标点之后统计一次 cnt 更新结果值 每次移动智能向右或者向下
时间：每个位置都有两个选择 O(2^(m*n))
空间：O（1）

过程中重复计算，所以可以使用备忘录数组，到一个点，先查备忘录，没有再递归
m n 都是 小于 100 的所以先创建备忘录

优化：
能否剪枝：不能

动态规划呢？
dp[i][j]   0<=i<m    0<=j<n 表示从 start 到 (i, j) 的次数
转移方程：
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

初始条件
dp[0][0] = 1

dp 空间优化尝试，用的是二维数组，每次更新中只牵扯到两个变量 用一维数组 + 一个变量 减少空间


经过总结：
递归是从结果往前推，需要的计算
递推是从前往结果推到，推导过程中记录计算得到的值


 */
public class UniquePaths_62 {

    private static int cnt;

    // 加备忘录
    public static int uniquePaths5(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

        int[][] mem = new int[101][101];

        return process2(mem, m - 1, n - 1);

    }

    private static int process2(int[][] mem, int row, int col) {
        if (row < 0 || col < 0) return 0;
        if (row == 0 || col == 0) return 1;

        if (mem[row][col] != 0) return mem[row][col];

        // update mem
        mem[row][col] = process(row - 1, col) + process(row, col - 1);

        return mem[row][col];
    }

    // 递归
    public static int uniquePaths4(int m, int n) {

        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

//        int[][] mem = new int[101][101];

        return process(m - 1, n - 1);

    }

    // 到达 row col 位置有多少种路径
    private static int process(int row, int col) {
        if (row < 0 || col < 0) return 0;
        if (row == 0 || col == 0) return 1;

//        if (mem[row][col] != 0) return;

        return process(row - 1, col) + process(row, col - 1);
    }

    public static int uniquePaths3(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

        int dp[] = new int[n];// 一行的dp 数组

        // init first row
        for (int j = 0; j < n; j ++) {
            dp[j] = 1;
        }

        int left = 1; // left dp
        for (int i = 1; i < m; i ++) {
            left = 1;
            for (int j = 1; j < n; j ++) {
                dp[j] = left + dp[j];
                left = dp[j];
            }
        }

        return dp[n - 1];
    }

    public static int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

        int[][] dp = new int[m][n];

        // init
        for (int i = 0; i < m; i ++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < n; j ++) {
            dp[0][j] = 1;
        }

        // update dp by row
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }




    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

        backtrack(m, n, 0, 0);

        return cnt;
    }

    private static void backtrack(int m, int n, int row, int col) {
        // 越界判断
        if (row >= m || col >= n) return;

        if (row == m - 1 && col == n - 1) {
            cnt ++;
        }

        // 向右
        backtrack(m, n, row, col + 1);
        // 向下
        backtrack(m, n, row + 1, col);
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
        System.out.println(uniquePaths2(7, 3));
        System.out.println(uniquePaths3(7, 3));

        System.out.println(uniquePaths4(7, 3));

        System.out.println(uniquePaths5(7, 3));
    }
}
