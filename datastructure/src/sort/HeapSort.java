package sort;

import java.util.Arrays;

/*
堆排序

数组模拟堆结构，这里使用大根堆
数组从 0 下标喀什存储，则  i 位置的左孩子是 2*i + 1 右孩子 2 * i +2
父亲： i-1 / 2
过程是，传进来的数组，常见一个辅助数据，根原来的数组大小一样
向堆中添加元素时，添加的最后一个位置，添加之后向上调整；
建立好堆之后，排序时，把堆定和堆中最后一个元素交换位置，交换之后自定向下调整堆
实际上，这个过程就不需要额外的数组，直接在原数组上操作
 */
public class HeapSort {

    public static void heapSort(int[] nums) {

        int n = nums.length;
        // build heap
        for (int i = 0; i < n; i++) {
            heapAddEle(nums, i);
        }

        // sort
        for (int i = n - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapUpToDown(nums, i);
        }
    }

    // 自顶向下调整堆，当前堆的最后一个节点时 i - 1
    private static void heapUpToDown(int[] nums, int i) {

        int idx = 0;
        while (getLeftChild(idx) < i) {
            int changeIdx = getLeftChild(idx);
            if (getRightChild(idx) < i && nums[getRightChild(idx)] > nums[getLeftChild(idx)]) {
                changeIdx = getRightChild(idx);
            }
            if (nums[idx] < nums[changeIdx]) {
                swap(nums, idx, changeIdx);
                idx = changeIdx;
            } else {
                break;
            }
        }
//        while (idx < i) {
//            int changeIdx = 0;
//            if (getLeftChild(idx) < i) {
//                changeIdx = getLeftChild(idx);
//            } else {
//                break;
//            }
//            if (getRightChild(idx) < i && nums[getRightChild(idx)] > nums[getLeftChild(idx)]) {
//                changeIdx = getRightChild(idx);
//            }
//            if (nums[idx] < nums[changeIdx]) {
//                swap(nums, idx, changeIdx);
//                idx = changeIdx;
//            } else {
//                break;
//            }
//        }
    }

    // 自当前节点 i 位置 向上调整堆 这里是大根堆
    private static void heapAddEle(int[] nums, int i) {

        // i 不是堆顶，且大于父节点
        while (i != getParent(i) && nums[i] > nums[getParent(i)]) {
            swap(nums, i, getParent(i));

            i = getParent(i);
        }
    }

    public static int getParent(int i) {
//        return i / 2;
        return (i - 1) / 2;
    }

    public static int getLeftChild(int i) {
        return 2 * i + 1;
    }

    public static int getRightChild(int i) {
        return 2 * i + 2;
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
            heapSort(arr1);
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
        heapSort(arr);
        printArray(arr);
    }

}
