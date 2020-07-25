package sort;

import java.util.Arrays;

/*
归并排序

分治法，原问题划分成子问题，子问题解决之后，解决原问题

外排时，注意，helpArr 是从 0 开始的，不是从 left  开始的
mid 的求法：left + (right - left) /2 ====== (left + right)/2
 */
public class MergeSort {

    public static void mergeSort(int[] nums) {
//        mergeProcess(nums, 0, nums.lengt);
        mergeProcess(nums, 0, nums.length - 1);
    }

    private static void mergeProcess(int[] nums, int left, int right) {
        if (left >= right) return;


//        int mid = (right - left) / 2;
        int mid = (right + left) / 2;

        mergeProcess(nums, left, mid);
        mergeProcess(nums, mid + 1, right);

        // 外排两个子序列
        outSort(nums, left, mid, right);
    }

    // 外排连个子序列 双指针
    private static void outSort(int[] nums, int left, int mid, int right) {
        int[] helpArr = new int[right - left + 1];

        int i = left, j = mid + 1;
//        int k = left;
        int k = 0; // 辅助数据的开始位置
        while (i <= mid && j <= right) {
            helpArr[k ++] = (nums[i] <= nums[j]) ? nums[i ++] : nums[j ++];
        }

        while (i <= mid) {
            helpArr[k ++] = nums[i ++];
        }
        while (j <= right) {
            helpArr[k ++] = nums[j ++];
        }

        k = 0;
        for (i = left; i <= right; ) {
            nums[i ++] = helpArr[k ++];
        }
    }


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        mergeSort(arr);
        printArray(arr);
    }
}
