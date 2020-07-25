package sort;

import java.util.Arrays;

/*
快排

 */
public class QuickSort {

    public static void quickSort(int[] nums) {
//        int n = nums.length;

        helpSort(nums, 0, nums.length - 1);
    }

    private static void helpSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int[] pivotIdx = partion(nums, left, right);

//        helpSort(nums, left, pivotIdx[0]);
//        helpSort(nums, pivotIdx[1], right);

        helpSort(nums, left, pivotIdx[0] - 1);
        helpSort(nums, pivotIdx[1] + 1, right);
    }

    // 根据最后一个元素划分数组，返回划分后 pivot 的上下界
    private static int[] partion(int[] nums, int left, int right) {
        int pivot = nums[right]; // 选取最后一个值作为 pivot 或者可以随便取
        int p = left; // 小于 pivot 数组的下一个位置
        int q = right; // 大于 pivot 的数组的第一个位置
        int i = left;
        while (i < q) {
            if (nums[i] < pivot) {
                swap(nums, p ++, i ++);
            } else if (nums[i] > pivot) {
                swap(nums, i, -- q);
            } else {
                i ++;
            }
        }

        swap(nums, i, right);
        return new int[]{p, q - 1};
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
            quickSort(arr1);
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
        quickSort(arr);
        printArray(arr);
    }
}
