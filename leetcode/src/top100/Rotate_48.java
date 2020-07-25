package top100;

/*
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

分析
1，线转置矩阵，再翻转每一行
2，按照外圈内圈矩形来旋转
外圈只需要旋转一半的行


定义左上角的位置为 [i, j]
那么另外四个位置的坐标；右上角 [j, n - 1 -i] 右下角[n - 1 -i, n-1 -j] 左下角[n-1-j, i]

 */
public class Rotate_48 {

    public static void rotate2(int[][] matrix) {
        int n = matrix.length;

        // i 控制横坐标，j 控制纵坐标
        for (int i = 0; i < n / 2; i ++) {
            // 每一圈中从左上角第一个到这一圈的最后
            for (int j = i; j < n - i - 1; j ++) {
                int temp = matrix[i][j];

                // 左下角占据左上角
                matrix[i][j] = matrix[n - 1 - j][i];
                // 右下角占据左下角
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                // 右上角占据右下角
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                // 左上角占据右上角
                matrix[j][n - 1 - i] = temp;

            }
        }
    }

    public static void rotate(int[][] matrix) {

        int n = matrix.length;

        // 转换矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
//                swap(matrix[i][j], matrix[j][i]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 每一行翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
//                swap(matrix[i][j], matrix[i][n - j - 1]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }

    }


    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        System.out.println("a: " + a + "b: " + b);
//        swap(a, b);
//        System.out.println("a: " + a + "b: " + b);
    }
}
