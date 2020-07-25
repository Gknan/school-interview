package search;

/*
二分查找
 */
public class BinarySearch {

    // 二分查找
    public static int binarySearch(int[] nums, int target) {

        // 定义left right
        int left = 0;
//        int right = nums.length;
        int right = nums.length - 1; //   ***** 这里一定要注意，是否减1

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;
            else if (target > nums[mid]) left = mid + 1;
            else if (target < nums[mid]) right = mid - 1;
        }

        // 若没有查到 异常处理
        return -1;
    }
}
