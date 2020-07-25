package top100;

/*
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。

分析：
1 回溯法 过程中记录最小路径
2 动态规划，倒推法
到达最后一个位置的最小路径是 dp[m][n] = min(dp[m - 1][n], dp[m][n - 1]) + grid[m][n]
dp[0][0] = grid[0][0]
向下求解，填矩阵，最终返回 dp[m][n]

3 不需要额外空间的方法，直接在原数组上存储 dp 的值

 */
public class MinPathSum_64 {

    public int minPathSum4(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;

        int m = grid.length;
        int n = grid[0].length;

        // 更新 第一列
//        for ()

        // 更新 第一行

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    // 第一行
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }

        return grid[m - 1][n - 1];
    }

    public int minPathSum3(int[][] grid) {

        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        // 更新第一行
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            int left = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                dp[j] = Math.min(dp[j], left) + grid[i][j];
                left = dp[j];
            }
        }

        return dp[n - 1];
    }


    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            // 第一列只能通过向下走的方式到达
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < n; i++) {
            // 第一行只能通过向右走的方式到达
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 每次更新只使用了上边和左边两个变量，尝试压缩空间
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }


        return dp[m - 1][n - 1];
    }


    //

    private int minPath = Integer.MAX_VALUE;

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;

        backtrack(grid, 0, 0, 0);
        return minPath;
    }

    private void backtrack(int[][] grid, int row, int col, int preSum) {
        // 越界判断
        if (row >= grid.length || col >= grid[0].length) return;

        // 找到目的点
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            minPath = Math.min(minPath, preSum + grid[row][col]);
            return;
        }

        backtrack(grid, row + 1, col, preSum + grid[row][col]);
        backtrack(grid, row, col + 1, preSum + grid[row][col]);
    }
}
