package top100;

/*
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

分析：
1，有序数组，
2，中位数，中间位置的数字，是按顺序排列的一组数据中居于中间位置的数
3，这里的中位数的意思是 nums1 和 nums2 进行合并后的数字的中位数，合并后的有序数组的中位数
4，若进行排序，nums1 和nums2 进行外排序，时间 O(Max(M,N))，然后就可以找到中位数
5，上面的方法求出了太多的荣誉信息，导致复杂度高；单纯的中位数，尝试二分查找看看
6，双指针，每次缩减每个数组的一般，尽量让两者有效位靠近，直到两者都剩下一个元素，返回两者平均值
7，注意这里的 int 和 double 的转换

 */
public class FindMedianSortedArrays_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        if (nums1 == null && nums2 == null) return -9999999.9;
        if (nums1 == null || nums1.length < 1)
            return nums2.length % 2 == 0 ? ((double)nums2[(nums2.length - 1) / 2]
                    + (double)nums2[(nums2.length - 1) / 2 + 1]) / 2
                    : (double) nums2[nums2.length / 2];
        if (nums2 == null || nums2.length < 1)
            return nums1.length % 2 == 0 ? ((double)nums1[(nums1.length - 1) / 2]
                    + (double)nums1[(nums1.length  - 1) / 2 + 1]) / 2
                    : (double) nums1[nums1.length / 2];

        // 可能 nums 中的元素为空
//        if (nums1.length < 1)


        // 比较
        int l1 = 0, r1 = nums1.length - 1;
        int l2 = 0, r2 = nums2.length - 1;

        // 奇数偶数个数的问题

        while (l1 != r1 || l2 != r2) {
            int mid1 = (r1 + l1) / 2;
            int mid2 = (r2 + l2) / 2;

            if ((r1 - l1) % 2 == 0 && (r2 - l2) % 2 == 0) {
                // 连个长度都是奇数
                if (nums1[mid1] == nums2[mid2]) return nums1[mid1];
                else if (nums1[mid1] > nums2[mid2]) {
                    r1 = mid1;
                    l2 = mid2;
                } else if (nums1[mid1] < nums2[mid2]) {
                    l1 = mid1;
                    r2 = mid2;
                }
            } else if ((r1 - l1) % 2 == 0 && (r2 - l2) % 2 != 0) {
                // nums1 奇数个 nums2 偶数个 奇数个有可能来自与 r1 == l1
                if (2 * nums1[mid1] == (nums2[mid2] + nums2[mid2 + 1])) return nums1[mid1];
                else if (nums1[mid1] > nums2[mid2]) {
                    r1 = mid1;
                    l2 = mid2 + 1;
                } else if (nums1[mid1] < nums2[mid2]) {
                    l1 = mid1;
                    r2 = mid2;
                }
            } else if ((r1 - l1) % 2 != 0 && (r2 - l2) % 2 == 0) {
                // nums1 偶数个 nums 2 奇数个
                // 偶数个元素是 l2 == r2 怎么处理
                if (nums1[mid1] + nums1[mid1 + 1] == 2 * nums2[mid2]) return nums2[mid2];
                else if (nums1[mid1] > nums2[mid2]) {
                    r1 = mid1;
                    l2 = mid2;
                } else if (nums1[mid1] < nums2[mid2]) {
                    l1 = mid1 + 1;
                    r2 = mid2;
                }
            } else if ((r1 - l1) % 2 != 0 && (r2 - l2) % 2 != 0) {
                // nums1 nums2 都是偶数个
                if (nums1[mid1] + nums1[mid1 + 1] == nums2[mid2] + nums2[mid2 + 1])
                    return ((double) nums2[mid2] + (double) nums2[mid2 + 1]) / 2;
                else if (nums1[mid1] > nums2[mid2]) {
                    r1 = mid1;
                    l2 = mid2 + 1;
                } else if (nums1[mid1] < nums2[mid2]) {
                    l1 = mid1 + 1;
                    r2 = mid2;
                }
            }

//            if ((r1 - l1) % 2 == 0) {
//                // 奇数个 nums1 mid = 正好是正位数的位置，更新的时候 直接 l = mid + 1 或者 mid - 1
//            } else {
//                // 偶数个 nums mid 是中间偏左的位置，取中位数比较，若减少 mid 增加 mid + 1
//                // 若这里还是去 mid 的值比较，就是说实际的中位数是比 mid 位置的值大一点
//                // 此时，若 mid 位置的值小，l 更新为 mid + 1；若 mid 的值大，r 更新为 mid
//            }
        }

        if (nums1.length == nums2.length) return ((double) nums1[l1] + (double) nums2[l2]) / 2;
        // 可能存在的问题是，左边整体太小就会使得右边不停往左走，直到等待到
        // 最好的方式是 左边太小就过期，跳出后，右边就是需要的值
        else if (nums1.length < nums2.length) return 0;

        return ((double) nums1[l1] + (double) nums2[l2]) / 2;
    }

    public static void main(String[] args) {

        int[] nums1 = {3};
//        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
