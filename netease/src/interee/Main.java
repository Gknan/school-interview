package interee;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] ret = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    ret[i][j] = 0;
                } else {
//                    ret[i][j] = backtrack(arr, i, j, 0, visited);
                    ret[i][j] = 1;
                }
            }
        }

//        Arrays.sort();

        for (int i = 0; i < n; i++) {
            int j = 0;
            for (; j < m - 1; j++) {
                System.out.print(ret[i][j] + " ");
            }
            System.out.println(arr[i][j]);
        }
    }

    public static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * 找 ij 位置最近的 0 的步数
     *
     * @return
     */
    private static int backtrack(int[][] arr, int row, int col, int preStep,
                                 boolean[][] visited) {

        if (arr[row][col] == 0) {
            return preStep;
        }

//        for (int i = row; i < arr.length; i++) {
//            for (int j = col; j < arr[0].length; j++) {
        int ret = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            int newRow = row + direction[k][0];
            int newCol = col + direction[k][1];
            if (newRow >= 0 && newRow < arr.length &&
                    newCol >= 0 && newCol < arr[0].length &&
                    visited[newRow][newCol]) {
                visited[newRow][newCol] = true;
                int cur = backtrack(arr, newRow, newCol, preStep + 1, visited);
                ret = Math.min(ret, cur);

                visited[newRow][newCol] = false;
            }
        }
//            }
//        }


//        int ret = Math.min(Math.min(up, down), Math.min(left, right));

        return ret;
    }
}
