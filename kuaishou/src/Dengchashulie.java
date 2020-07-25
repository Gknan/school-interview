/*
给定一个未排序数组,找出其中最长的等差数列(无需保证数字顺序)。
 */

import java.util.Arrays;
import java.util.Scanner;

public class Dengchashulie {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        int[] arr = new int[n];
//        for  (int i = 0; i < n; i ++) {
//            arr[i] = scanner.nextInt();
//        }

        int n = 11;
        int[] arr = {3 ,2 ,1 ,6 ,7 ,10, 9 ,13 ,12 ,14 ,15};
//
        if (n < 3) {
            System.out.println(n);
            return;
        }

        Arrays.sort(arr);

        int low = arr[0]; // 最小值
        int high = arr[n - 1]; // 最大值

        int[][] dp = new int[n][high - low + 1]; // 以第 i 个数作为结尾，等差为 j 的长度

        int max = 0; // 空间换时间的思路
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) { // 遍历 i 之前的所有元素 计算他们的等差
                // arr[i] 与 arr[j] 的等差
                int d = arr[i]- arr[j];
                dp[i][d] = dp[i - 1][d] + 1;
                // 当前 arr[i] 作为等差的末尾等于 前一个arr[i - 1] 作为等差的末尾
                max = Math.max(max, dp[i][d]);
            }
        }

        System.out.println(max + 1);
        

//        int[] dp = new int[high + 1];
////        Arrays.fill(dp, 1); // 以自己作为结尾的等差数列的长度
//        for (int i = 0; i < n; i++) {
//            dp[arr[i]] = 1;
//        }
//
//        for (int i = 0; i < n; i++) {
//            int max = Integer.MIN_VALUE;
//            for (int j = 1; arr[i] - j >= low; j ++) {
//                if (dp[arr[i] - j] == 0) continue;
//                max = Math.max(max, dp[arr[i] - j]);
//            }
//            dp[i] = Math.max(max + 1, dp[i]);
//        }
//
//        int ans = 0;
//        for (int i = 0; i < high + 1; i++) {
//            ans = Math.max(dp[i], ans);
//        }
//
//        System.out.println(ans);
//
//        // 检查拍完序的数组的最长等差数列长度
//        int[] midSubstr = new int[n - 1];
//        for (int i = 1; i < n; i ++) {
//            midSubstr[i - 1] = arr[i] - arr[i - 1];
//        }

//        int ret = 0;
//        int subMax = 1;
//        int curVal = midSubstr[0];
//        // 统计 midSubStr 中的连续相同的最大长度
//        for (int i = 1; i < n - 1; i ++) {
//            if (midSubstr[i] == curVal) {
//                subMax ++;
//            } else {
//                // 若不等；更新统计 subMax 更新 ret curVal
//                ret = Math.max(ret, subMax);
//
//                subMax = 1;
//                curVal = midSubstr[i];
//            }
//        }
//
//        // 最后需要再统计一些首尾工作
//        ret = Math.max(ret, subMax);
//
//        System.out.println(ret + 1);
    }
}
