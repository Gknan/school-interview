package top100;

import java.util.PriorityQueue;

/*
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，
而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

分析：
1，数组降序排序之后，返回第 k - 1个位置的数字 时间 O(nlogn) 空间 O(1)
2，插入 冒泡排序 时间 O(kn)
3，借助堆，小根堆，维护数组中的前k大数字，遍历数组，更新堆结构，返回堆顶部 时间 O(nlogk) 空间O(k)
4，相当于找有序结构的第 k 个位置，快排每次确定一个位置，若一趟排序之后是第 k 个数，直接返回
，若不是，分治，查找有k的那一半

 */
public class FindKthLargest_215 {

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        assert n >= k;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i ++) {
            if (i < k) {
                minHeap.add(nums[i]);
                continue;
            }

            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        return minHeap.peek();
    }

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
