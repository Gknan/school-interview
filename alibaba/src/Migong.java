/*
走迷宫游戏；位于起点，目标是 尽快 达到终点
每一次可以选择话费一个时间单位 上下左右 移动一个；或者使用飞行器移动到关于点中心对称的各自；
不能落在障碍物上；迷宫 n * m
对称位置满足 x + x'= n + 1 y + y' = m + 1
对称飞行器最多使用 5 次

遍历所有情况，

 */

public class Migong {

    int ret = Integer.MAX_VALUE;
    public int aimTarget(int[][] matrix) {

        process(matrix, 0, 0, 0, 5);

        return ret;
    }

    /**
     *
     * @param matrix
     * @param row
     * @param col
     * @param preSum
     * @param remFly 还剩的飞行次数
     */
    private void process(int[][] matrix, int row, int col, int preSum, int remFly) {
        // 无效位置
        if (row < 0 || row >= matrix.length || col < 0 ||
        col >= matrix[0].length || matrix[row][col] != 0 || remFly < 0 || preSum > ret) {
            //  preSum > ret 剪枝
            return;
        }

        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            ret = Math.min(preSum, ret);
        }

        // 上下左右移动
        process(matrix, row - 1, col, ++ preSum, remFly);
        process(matrix, row + 1, col, ++ preSum, remFly);
        process(matrix, row, col - 1, ++ preSum, remFly);
        process(matrix, row, col + 1, ++ preSum, remFly);

        // 对称移动
        process(matrix, matrix.length - 1 - row, matrix[0].length - 1 - col, ++ preSum, -- remFly);
    }

}
