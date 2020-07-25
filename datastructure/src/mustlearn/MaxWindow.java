package mustlearn;

import java.util.Arrays;
import java.util.LinkedList;

/*
滑动窗口问题

维护的是单调递减的数组，那么可以确保的是在处理 j 位置时，
里面都是比 j 元素打的元素的下标；取出前，为了求窗口内的最大值，所以需要判断
对首是否过期，过期就移除。

 */
public class MaxWindow {

    public static int[] maxWindwo(int[] arr, int k) {
        // 主要是这里的边界处理，除了很多问题
        if (k == 0 || arr.length == 0) return arr;

        LinkedList<Integer> window = new LinkedList<>();

        int[] ret = new int[arr.length - k + 1];
        int idx = 0; // 注意这里的 ret 的数组的起始位置和原来数组的其实位置不一样

        for (int i = 0; i < arr.length; i++) {

            while (!window.isEmpty() && arr[window.getLast()] <= arr[i]) {
                window.removeLast();
            }
            // 去除 window 中过期的元素
            while (!window.isEmpty() && i - window.getFirst() > k - 1) {
                window.removeFirst();
            }

            window.addLast(i);
            if (i >= k - 1) {
                ret[idx ++] = arr[window.getFirst()];
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println(Arrays.toString(maxWindwo(arr, k)));
    }
}
