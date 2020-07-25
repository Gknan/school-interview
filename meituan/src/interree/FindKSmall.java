package interree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 找一个无序数组的第 k 小元素
 *
 * 方法1，排序后返回第 k 个，时间复杂度 O(nlogn)
 * 法2：冒泡排序，比较排序，时间复杂度 O(kN)
 * 法3：堆排序，时间 O(nlogk) 空间 O(k)
 * 法4：二分排序，每次排序一部分
 *
 */
public class FindKSmall {

    /**
     * 直接排序后返回
     * @param arr 无需数组
     * @param k 第 k 个数
     * @return 第 k 小的数
     */
    public static int method1(int[] arr, int k) {
        if (arr == null || arr.length < k) return -1;

        Arrays.sort(arr);
        return arr[k - 1];
    }

    /**
     * 法二，使用冒泡排序法
     * @param arr
     * @param k
     * @return
     */
    public static int method2(int[] arr, int k) {
        if (arr == null || arr.length < k) return -1;

        int n = arr.length;
        for (int i = n - 1; i >= k - 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j + 1, j);
                }
            }
        }

        return arr[k - 1];
    }

    /**
     * 比较排序
     * @param arr
     * @param k
     * @return
     */
    public static int method3(int[] arr, int k) {
        if (arr == null || arr.length < k) return -1;

        int n = arr.length;
        for (int i = 0; i < k; i++) {
            // i + 1 到 n - 1 的最小值
            int min = arr[i];
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swap(arr, minIdx, i);
            }
        }

        return arr[k - 1];
    }

    /**
     * 堆排序法
     * @param arr
     * @param k
     */
    public static int method4(int[] arr, int k) {
        if (arr == null || arr.length < k) return -1;

        int n = arr.length;
        // 建立大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < n; i++) {
            // 前 k 个元素加入到 堆中
            if (i < k) {
                maxHeap.add(arr[i]);
                continue;
            }
            // 比较
            if (maxHeap.peek() > arr[i]) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        return maxHeap.peek();
    }

    /**
     * 二分法，快排减少解空间
     * @param arr
     * @param k
     * @return
     */
    public static int method5(int[] arr, int k) {

        if (arr == null || arr.length < k) return -1;

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int[] pivot = partition(arr, left, right);

            // 无需数组可能有重复元素
            if (k - 1 >= pivot[0] && k - 1 <= pivot[1]) {
                // 找到解
                return arr[pivot[0]];
            } else if (k - 1 < pivot[0]) {
                right = pivot[0] - 1;
            } else if (k - 1> pivot[1]) {
                left = pivot[1] + 1;
            }
        }

        return arr[left];
    }

    // 对 left ~ right 之间的区域进行划分

    /**
     *
     * @param arr
     * @param left
     * @param right
     * @return 返回等于 pivot 的左边和有边边界
     */
    private static int[] partition(int[] arr, int left, int right) {

        int p = left; // 小于pivot 区域最后一个值的下一个
        int q = right; // 大于 pivot 区域的第一个值
        int pivot = arr[right]; // 本次选择的比较值
        int i = left; // 遍历的右边，从 left 开始
        while (i < q) {
            if (arr[i] > pivot) {
                swap(arr, i, -- q);
            } else if (arr[i] < pivot) {
                swap(arr, i ++, p ++);
            } else {
                i ++;
            }
        }

        // 最后交换 pivot 和 i 位置
        swap(arr, q, right);

        return new int[]{p, q};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // 测试
        int[] arr = {4, 1, 3, 5, 7, 3, 2, 8};
        int k = 100;
        System.out.println(method1(arr.clone(), k));
        System.out.println(method2(arr.clone(), k));
        System.out.println(method3(arr.clone(), k));
        System.out.println(method4(arr.clone(), k));
        System.out.println(method5(arr.clone(), k));
    }
}
