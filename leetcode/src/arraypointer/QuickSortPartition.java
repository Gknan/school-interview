package arraypointer;

public class QuickSortPartition {

    // 分治法解决
    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        assert n >= k;

        // 减治解决
        int left = 0;
        int right = n - 1;
        // 第 k 大的值就是 正序排序的第 n - k 小的值
        int ksmall = n - k;
        while (left < right) {
            // 划分
            int pivotIdx = patition(nums, left, right);
            if (pivotIdx == ksmall) {
                return nums[pivotIdx];
            } else if (pivotIdx < ksmall) {
                // pivot + 1 之后找
                left = pivotIdx + 1;
            } else {
                // left ~ pivot - 1 之间找
                right = pivotIdx - 1;
            }

        }

        return nums[left];
    }

    // 返回中枢的下标
    // p 是小于 pivot 的最后一个数的下一个位置；q 是大于 pivot 的第一个数
    private int patition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int p = left, q = right;
        int i = left;
        while (i < q) {
            if (nums[i] < pivot) {
                swap(nums, p, i);
                p ++;
                i ++;
            } else if (nums[i] > pivot) {
                q --;
                swap(nums, q, i);
//                q --;
            } else {
                i ++;
            }
        }

        swap(nums, right, q);
        return q;
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
