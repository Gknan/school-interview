package top100;

/*
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

分析：
根据矩阵的特性，从右上角开始搜索
记当前的位置元素为 cur；
若 target == cur; return ture;
   tareget > cur; 找比 cur 大的位置，即向下移动
   target < cur; 找 cur 小的位置，向左移动
直到超过边界，说明没有找到，返回 false

 */
public class SearchMatrix_240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m, n;
        if ((m = matrix.length) < 1 || (n = matrix[0].length) < 1) return false;

        int row = 0, col = n - 1;
        while (isValid(row, col, m)) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else if (matrix[row][col] < target) row++;
        }

        return false;
    }

    private boolean isValid(int row, int col, int m) {
        return row < m && col >= 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int target = 5;

        System.out.println(new SearchMatrix_240().searchMatrix(matrix, target));
    }
}
