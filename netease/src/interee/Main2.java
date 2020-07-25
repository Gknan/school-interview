package interee;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] arr = new long[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextLong();
        }

        if (n == 0) {
            System.out.println(-1);
            return;
        }
        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }
//
//        int n = 4;
//        int[] arr = {1, 3, 7, 15};


//        int n = 4;
//        int[] arr = {1, 2, 1, 1};


        long [] dif = new long[n - 1];
        int j = 0;
        boolean neg = false;
//        boolean zero = false;
        for (int i = 1; i < arr.length; i++) {
            dif[j] = arr[i] - arr[i - 1];
            if (dif[j] <= 0){
                neg = true;
                break;
            }
            j ++;
        }

        if (neg) {
            System.out.println(-1);
            return;
        }

        // 先排序
        Arrays.sort(dif);
        // 从 最小的值往下找
        long start = dif[0];
        boolean isFind = false;
        for (long i = start; i > 0; i --) {
            boolean flag = false;
            for (int k = 0; k < dif.length; k++) {
                if (dif[k] % i != 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(i);
                isFind = true;
                break;
            }
        }

        if (!isFind)
            System.out.println(-1);

//        Arrays.sort();
    }
}
