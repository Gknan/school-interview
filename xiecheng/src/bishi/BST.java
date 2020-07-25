package bishi;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class BST {

    // 二分查找 返回下标
    public static int bst(int[] nums, int k) {
        // 若 nums 没有 重复

        int n = nums.length;

        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == k) return mid;
            else if (nums[mid] > k) {
                // left range
                r = mid - 1;
            } else if (nums[mid] < k) {
                // right range
                l = mid + 1;
            }
        }

        return nums[l] == k ? l : -1;
    }

    // 若有重复的数字
    // 返回重复数字的最右边
    public static int bst2(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n;

        // 考虑排除一定不是饥解的部分，缩小搜索空间
        while (l < r) {
            int mid = l + ((r - l) / 2);
            if (nums[mid] == k) {
                // 相等时，收缩左边界，因为我们找的是重复的最右边；最终解要存在，一定在 left - 1
                l = mid + 1;
            } else if (nums[mid] < k) {
                l = mid + 1;
            } else if (nums[mid] > k) {
                r = mid;
            }
        }

        // l 取值范围 [0, n] 0 表示 有 0 个数比 k 小，n 表示有 n 个数比 k 小
        if (l == 0) return -1;

        return nums[l - 1] == k ? l - 1: -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length < 1) return new int[]{-1, -1};

        int n = nums.length;
        int leftBorder = bstLeft(nums, target);
        int rightBorder = bstRight(nums, target);

        return new int[]{leftBorder, rightBorder};
    }

    // 若有重复元素，返回右边界
    private int bstRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                // 收缩左边界
                l = mid + 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            }
        }

        // l - 1 是解的位置
        if (l == 0) return -1;
        return nums[l - 1] == target ? l - 1 : -1;
    }

    // 二分查找最左边界
    private int bstLeft(int[] nums, int target) {

        int r = nums.length;
        int l = 0;

        // 1 2 2 2 3 作为例子分析
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                // 收缩右边界
                r = mid; // 分成 [left, mid) [mid + 1, right)
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            }
        }

//        if (l == 0) return nums[l] == target ? l : -1;
//        return l == nums.length ? -1 : l;
        // l 的范围 [0, n]
        if (l == nums.length) return -1;
        return nums[l] == target ? l : -1;
    }


    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,4};
//        System.out.println(bst2(nums, 2));

//        System.out.println(new BST().bstLeft(nums, 1));
        ConcurrentHashMap<String, String > map = new ConcurrentHashMap<>();
        map.put("1", "1");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("2", "2");
    }
}
