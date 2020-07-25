package year2020_interee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
小易给你一个包含n个数字的数组。你可以对这个数组执行任意次以下交换操作：
对于数组中的两个下标i,j(1<=i,j<=n)，如果为奇数，就可以交换a_i和a_j。

现在允许你使用操作次数不限，小易希望你能求出在所有能通过若干次操作可以得到的数组中，字典序最小的一个是什么。

输入描述:
第一行一个整数n；
第二行n个整数a_1,a_2,..,a_n，表示数组，每两个数字之间用一个空格分隔。
输入保证。

输出描述:
n个整数，每两个整数之间用一个空格分隔，表示得到的字典序最小的数组。

输入例子1:
4
7 3 5 1

输出例子1:
7 3 5 1

输入例子2:
10
53941 38641 31525 75864 29026 12199 83522 58200 64784 80987

输出例子2:
12199 29026 31525 38641 53941 58200 64784 75864 80987 83522

分析：
从每个位置开始，遍历向后找，找到第一个与自己相加奇数的数字，并且这个数字要小于当前数字，且是下与当前数字的最小值，于其交换；若
向后比较了一圈，没有满足条件的元素，向后移动一位

 */
public class Main {

    public static class Pair{
        public int idx;
        public int val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = scanner.nextInt();
        }

//        int[] arr = {53941, 38641, 31525, 75864, 29026, 12199, 83522, 58200, 64784, 80987};
//        int[] arr = {7, 3, 5, 1};
        int oddCnt = 0;
        int evenCnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenCnt ++;
            } else {
                oddCnt ++;
            }
        }

        help(arr);

        if (oddCnt == evenCnt) {
            Arrays.sort(arr);
        }

//        System.out.println(Arrays.toString(arr));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i ++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb.toString());
    }

    /**
     * 在原数组上进行处理，得到最终的数组，最小字典序的数组
     * @param arr
     */
    private static void help(int[] arr) {
        //
        int n = arr.length;
        for (int i = 0; i < n; ) {
            int cur = arr[i];
            // 向后找最小的符合 arr[j] + cur 且 arr[j] < cur 的元素
            PriorityQueue<Pair> minHeap = new PriorityQueue<>(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.val - o2.val;
                }
            });
            for (int j = i + 1; j < n; j ++) {
                if (arr[j] < cur && (arr[j] + cur) % 2 == 1) {
                    minHeap.add(new Pair(j, arr[j]));
                }
            }
            if (minHeap.size() > 0) {
                // 找到一个交换的对象
                // 继续找
                swap(arr, minHeap.peek().idx, i);
            } else {
                i ++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
