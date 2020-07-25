package sort;

import java.util.Arrays;

/*
插入排序
思想：
数组分为两部分，前一部分是有序的，后一部分是无序的
i 指向当前的需要处理的元素，0 ~i-1 是有序数组，那么向前查找 i 的位置，即找到第一个小于
等于 i 的位置插入进去，然后从该位置开始，
所有的元素向后移动；但是为了减少操作，我们在比较的同时就交换位置，

 */
public class InsertSort {

    public static void insertSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i ++) {
            // 保存 i 的值
            int cur = nums[i];
            for (int j = i - 1; j >= 0; j --) {
                // 画图示意，这种方案还是容易出错
//                if (nums[j] > cur) {
////                    nums[j + 1] = nums[j];
////                } else {
////                    nums[j] = cur;
////                    break;
////                }
                if (nums[j] > cur) {
                    swap(nums, j, j + 1);
                }
            }
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
            insertSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        insertSort(arr);
        printArray(arr);
    }
}
