
/*
给定一个数组，每个元素范围是0~K（K < 整数最大值2^32），将该数组分成两部分，使得 |S1- S2|最小，其中S1和S2分别是数组两部分的元素之和。



输入描述:
数组元素个数N（N 大于1但不超过 10, 000, 000）

数组中N个元素（用空格分割)
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class DivideSection {

    public static void main(String[] args) {


//
//        int n = 4;
//
//        int[] arr = {1,1,1,999};

//
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Arrays.sort(arr);

        // 找平均值
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        long ave = sum / 2;

//        LinkedList<Integer> stack = new LinkedList<>();
        for (int j = arr.length - 1; j >= 0; j --) {
            if (ave - arr[j] > 0) {
                ave -= arr[j];
            } else if (ave - arr[j] == 0) {
                if (sum % 2 == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
                return;
            }
        }

        long ret = Math.abs((sum - (sum / 2 - ave)) - (sum / 2 - ave));
        System.out.println(ret);

    }
}
