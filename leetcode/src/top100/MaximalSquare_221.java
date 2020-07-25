package top100;

/*
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4

分析：
暴力法：以每个位置开始，以当前位置作为正方形的左上角，扩展求每个位置对应的最大正方行，过程中使用全局变量记录
所有里面的最大值，返回即可
如果当前位置是 0 ，它肯定不是最大正方式的一部分，
若当前位置是 1，

dp[i][j] 以 [i][j] 作为正方行的右下角位置，组成的最大正方行的边长
递推公式：
    dp[i][j] = 0    matrix[i][j] = 0;
    dp[i][j] = 1 + min(dp[][], dp[][], dp[][])    matrix[i][j] = 1;

未通过 case，[[1]]

 */
public class MaximalSquare_221 {

    public int maximalSquare(char[][] matrix) {

        int m, n;
        if ((m = matrix.length) < 1 || (n = matrix[0].length) < 1) return 0;

        int ledge = 0;
        int[][] dp = new int[m][n];

        // init
        for (int col = 0; col < n; col ++) {
            dp[0][col] = Character.digit(matrix[0][col], 10);
            ledge = Math.max(ledge, dp[0][col]);
        }
        for (int row = 0; row < m; row ++) {
            dp[row][0] = Character.digit(matrix[row][0], 10);
            ledge = Math.max(ledge, dp[row][0]);
        }

        for (int row = 1; row < m; row ++) {
            for (int col = 1; col < n; col ++) {
                if (matrix[row][col] == '0') dp[row][col] = 0;
                else dp[row][col] = 1 + Math.min(Math.min(dp[row][col - 1], dp[row - 1][col]),
                        dp[row - 1][col - 1]);

                // update ledge
                ledge = Math.max(ledge, dp[row][col]);
            }
        }

        return ledge * ledge;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        };

        System.out.println(new MaximalSquare_221().maximalSquare(matrix));
    }
}
