package year2020;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Scanner;

/*
打车派单场景, 假定有N个订单， 待分配给N个司机。每个订单在匹配司机前，会对候选司机进行打分，打分的结果保存在N*N的矩阵A， 其中Aij 代表订单i司机j匹配的分值。

假定每个订单只能派给一位司机，司机只能分配到一个订单。求最终的派单结果，使得匹配的订单和司机的分值累加起来最大，并且所有订单得到分配。


输入描述:
第一行包含一个整数N，2≤N≤10。
第二行至第N+1行包含N*N的矩阵。

输出描述:
输出分值累加结果和匹配列表，结果四舍五入保留小数点后两位
（注意如果有多组派单方式得到的结果相同，则有限为编号小的司机分配编号小的订单，比如：司机1得到1号单，
司机2得到2号单，就比司机1得到2号单，司机2得到1号单要好）

输入例子1:
3
1.08 1.25 1.5
1.5 1.35  1.75
1.22 1.48 2.5

输出例子1:
5.25
1 2
2 1
3 3

例子说明1:
第一行代表得到的最大分值累加结果5.25，四舍五入保留两位小数；

第二行至第四行代表匹配的结果[i j],其中i按行递增：

订单1被派给司机2，订单2被派给司机1，订单3被派给司机3。使得A12+ A21+ A33= 1.25 + 1.5 + 2.5 = 5.25在所有的组合中最大。

八皇后问题 回溯 + 剪枝

 */
public class Order {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        double[][] arr = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                arr[i][j] = scanner.nextInt();
                arr[i][j] = scanner.nextDouble();
            }
        }
//
//        double[][] arr = {
//                {1.08, 1.25, 1.5},
//                {1.5, 1.35,  1.75},
//                {1.22, 1.48, 2.5}
//        };
        // prePath 中应该放到是已经选过的 列下标
        LinkedList<Integer> preCol = new LinkedList<>();
        LinkedList<Double> prePath = new LinkedList<>();
        // 按行选，每行选司机
        help(arr, 0, prePath, preCol, 0);
        // (double) Math.round(dev*100) / 100
//        double result = new BigDecimal(ret).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double result = (double) Math.round(ret * 100) / 100;
//        System.out.println();
        System.out.println(String.format("%.2f", result));
//        System.out.println(retCol);
        int i = 1;
        for (int j: retCol) {
            System.out.println(i++ + " " + (j + 1));
        }
    }

    static double ret = -100.0;
    static LinkedList<Integer> retCol = new LinkedList<>();
    private static void help(double[][] arr, int row, LinkedList<Double> prePath,
                             LinkedList<Integer> preCol, double preSum) {

        // 无效边界

        if (row == arr.length) {

//            System.out.println(preCol);

            // 统计比较结果
            double sum = 0;
            for(double cur: prePath) {
                sum += cur;
            }
//if(sum == 5.75)   System.out.println(prePath);
            if (ret < sum) {
                ret = sum;
                while (!retCol.isEmpty()) {
                    retCol.removeLast();
                }
                for(int col: preCol) {
                    retCol.add(col);
                }
//                System.out.println(retCol);
            }
//            ret = Math.max(ret, sum);

            return;
        }

        for (int col = 0; col < arr.length; col++) {
            // 每列的选择
            if (preCol.contains(col)) continue;
            prePath.add(arr[row][col]);
            preCol.add(col);
            help(arr, row + 1, prePath, preCol, preSum + arr[row][col]);
            prePath.removeLast();
            preCol.removeLast();
        }
    }
}
