package interee;

import java.awt.datatransfer.StringSelection;
import java.math.BigDecimal;
import java.util.*;

/*

 */
public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int n = scanner.nextInt();
//
//        int [] arr = new int[n];
////        int [] arr = {1, 2, 3, 4, 5};
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = scanner.nextInt();
//        }

        int n = 3;
        int[] arr = {1, 3, 5, 3, 4};

        method2(arr);

//        Map<Integer, Integer> val2cnt = new HashMap<>();
//        for (int i = 0; i < n; i++) { // 初始化
//            val2cnt.put(arr[i], 0);
//        }
//
//
//        int sum = 0;
//        for (int i = 0; i < n; i++) {
//            // 以第 i 个位子作为头的子序列 分别统计 最大值，更新 map
//            for (int j = i; j < n; j++) {
//                sum++;
//                update(arr, val2cnt, i, j);
//            }
//        }
//
//        // 计算结果
//        double ret = 0;
//
//        for (int val : val2cnt.keySet()) {
//            ret += val * val2cnt.get(val);
//        }
//
//        ret /= sum;
//        System.out.println(String.format("%.6f", ret));

//        // 四舍五入
//        System.out.println(String.format("%.1f",3.66));
    }


    // 单调栈维护每个位置的左右可扩展边界
    public static void method3(int[] arr) {
        //
        int n = arr.length;
        int [] left = new int[n];
        int [] right = new int[n];

        LinkedList<Integer> stack = new LinkedList<>();
        // 从左到右使用单调栈更新 left 
        for (int i = 0; i < n; i++) {
//            while ()
        }


    }


    // 分析，使用 left[] right[] 数组存放第 i 个位置所能扩展的最长子序列的左边界和右边界，i 在这个最长子序列中是最大的
    // 如 7 1 2 4 2 5  对于 4 而言， left[3] = 1 right[3] = 4 那么在这个序列里，i 作为最大值的子序列个数为 (i - left[3] + 1) * (rigth[3] - i + 1)
    // 3 * 2 = 6； 1 2 4 2 的 4 作为最大值的子序列个数为6，分别是 124 1242 24 242 4 42 6个
    // 所以求出两个 left right 数组之后，就可以遍历得到每个位置的数字对应的子序列个数
    // 怎么更细 left right 数组呢？
    // left 表示的是 从 i 位置想左找，第一个大于 arr[i] 的位置的下一个位置
    // right 表示的是 从 i 位置向右在，第一个大于 arr[i] 的位置的前一个位置
    public static void method2(int[] arr) {

        int n = arr.length;

        int[] left = new int[n];
        int[] right = new int[n];

        // update left
        for (int i = 0; i < n; i++) {
            int cur = i;
            // 往前匹配
            int pre = cur - 1;
            while (pre >= 0) {
                if (arr[pre] <= arr[cur]) pre = left[pre] - 1;
                else {
                    // arr[pre] > arr[cur]
                    break;
                }
            }
            left[cur] = pre + 1;
        }

        // update right
        for (int j = n - 1; j >= 0; j --) {
            int cur = j;
            int post = cur + 1;
            while (post < n) {
                if (arr[post] <= arr[cur]) post = right[post] + 1;
                else break;
            }
            right[cur] = post - 1;
        }

        //
        int cnt = 0; //记录子序列的个数
        double sum = 0; // 记录 val * 频次
        for (int i = 0; i < n; i++) {
            int curCnt = (i - left[i] + 1) * (right[i] - i + 1);
            sum += (curCnt * arr[i]);
            cnt += curCnt;
        }


        System.out.println(String.format("%.6f", sum / cnt));
    }

    /**
     * 统计 arr[i~j] 子串中的最大值 更新 map
     *
     * @param arr     数组
     * @param val2cnt key 为最大的子序列个数
     * @param i       arr 子序列的其实下标 包括
     * @param j       arr 子序列的结束下标 包括
     */
    private static void update(int[] arr, Map<Integer, Integer> val2cnt, int i, int j) {
        int max = arr[i];
        for (int k = i + 1; k <= j; k++) {
            max = Math.max(max, arr[k]);
        }

        val2cnt.put(max, val2cnt.get(max) + 1);
    }
}
