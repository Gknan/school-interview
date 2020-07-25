package top100;

/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1

分析：
先找到分界点的位置，即旋转后数组中间最小的值，即后半段的第一个值
然后先从第一个区域搜索，再从第二个区域搜索

 */
public class Search_33 {
    public int search(int[] nums, int target) {

        int n;
        if ((n = nums.length) < 1) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;

        if (nums[0] < nums[n - 1]) {
            // 一定是正序
            return find(nums, 0, n - 1, target);
        } else {
            // 先找出分解位置
            int idx = findLoc(nums, 0, n - 1);

            if (nums[0] <= target) {
                return find(nums, 0, idx - 1, target);
            } else {
                return find(nums, idx, n - 1, target);
            }
        }
    }

    /**
     * 找到分界后的后半段的第一个元素的位置
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int findLoc(int[] nums, int start, int end) {

        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] > nums[end]) {
                // 向右缩
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                // 要找的位置肯定在 mid 或者mid 的左边
                end = mid;
            } // 只要不是一个元素，就一定不会存在 mid = end
        }

        return start;
    }

    /**
     * 在数组 l r 之间二分查找 target
     * 数组不存在重复元素
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int find(int[] nums, int start, int end, int target) {
        if (start > end) return -1;

        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) end = mid - 1;
            else if (nums[mid] < target) start = mid + 1;
        }

        return nums[start] == target ? start : -1;
    }

    public static void main(String[] args) {
        int[] nums = {7, 0, 1, 2, 4, 5, 6};
        int target = 1000;

        System.out.println(new Search_33().search(nums, target));
    }
}
