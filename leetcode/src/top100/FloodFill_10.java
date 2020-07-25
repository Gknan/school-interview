package top100;

/*
颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、
一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
示例1:
 输入：
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
 输出：[[2,2,2],[2,2,0],[2,0,1]]
 解释:中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色
在图像的正都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
说明：

image 和 image[0] 的长度在范围 [1, 50] 内。
给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。

分析：
把 sr sc 坐标周围和它一样的元素换成新的一样的 值
 */
public class FloodFill_10 {

    //
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        process(image, sr, sc, newColor, image[sr][sc]);

        return image;
    }

    public static void process(int[][] image, int sr, int sc, int newColor, int initColor) {
        if (sr < 0 || sr >= image.length || sc < 0
                || sc >= image[0].length ||
                image[sr][sc] != initColor || image[sr][sc] == newColor) {
            // image[sr][sc] == newColor 相等于去掉重复路径
            return;
        }
        // 赋新值
        image[sr][sc] = newColor;

        // DFS 四个方向
        process(image, sr - 1, sc, newColor, initColor);
        process(image, sr + 1, sc, newColor, initColor);
        process(image, sr, sc - 1, newColor, initColor);
        process(image, sr, sc + 1, newColor, initColor);
    }

    private static void print2DMatrix(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int[][] ints = floodFill(image, 1, 1, 2);
        print2DMatrix(ints);
    }
}
