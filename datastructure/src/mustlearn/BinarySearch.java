package mustlearn;

/**
 *
 二分查找框架分析：

 */
public class BinarySearch {
//
//    int binarySearch(int[] nums, int target) {
//        int left = 0;
//        int right = ....;
//
//        // 把所有的 else if 都写清除，方便展现所有的细节
//        while (....) {
//            int mid = (right + left) / 2;
//            if (nums[mid] == target) return mid;
//            else if (nums[mid] < target) left = ...
//            else if (nums[mid] > target) right = ...
//        }
//
//        return ...;
//    }


    // 搜索含有重复元素的最左边界
    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (target == nums[mid]) {
                // 向左扩展 压缩
                right = mid; // 因为相当于 mid 位置 已经选过了
            } else if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }

        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }


}
