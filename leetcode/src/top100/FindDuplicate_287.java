package top100;

/*
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知
至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:

输入: [1,3,4,2,2]
输出: 2
示例 2:

输入: [3,1,3,4,2]
输出: 3
说明：

不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。

分析：
1，借助 map 保存每个数字出现的个数 map 可以用 数组实现 空间复杂度不符合要求
2，先对数组排序，排序后皆可以找到，不能更改原数组 抛弃
3，对于每个数字，查找其在后面出现的次数，两重循环 时间为O(n2)
4，先用 n/2 ，遍历数组，通过 小于 n/2 的个数 min 大于 n/2 的个数 max
若 min <max 说明重复的数字大于 n/2 ；下一个循环使用 n/4 来统计；
若 min > max 说明重复的数字小于 n/2 equal 统计等于的个数
中位数 mid 数组中小于等于 mid 的个数 cnt
举例 1 2 2 3 4 5 6 7 n = 7 mid = 7 + 1 / 2 = 4 根据抽屉原理，若 cnt > 4; 则重复值一定小于 mid
中位数 = right + left  / 2
5，映射为链表的解法
1 2 3 4 5 6 7 8 9 5
从 0 下标开始，数组的值相当于下一个位置的指针，得到的链表是 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 5, 6, 7, 8 ,9 出现了环
若没有重复 如 1 2 3 4 得到的链表 0 ，1， 2， 3， null 最终越界
而重复的数字就是环的入口节点
所以，问题转换为求环的入口节点；分成两部，第一，快慢指针找到相交点；第二，设置第三个指针，和满指针一起找到入口
假设 slow fast 相交是，slow 走了n，fast 走了 2n；
环入口到开始的位置为 k； 2n - n 是fast 比 slow 多走的位置，实际上就是环内的位置有 n%c = 0; c 是 环的周长
那么，slow 在环内走的距离是 n - m，若 slow 再向前走 m 步，则在环内的距离就是 n；n % c = 0; 所以就到了环的入口

注意：这里我也想到了二分法，但是无法将 mid 的转换反映到更新到，这里使用的是不停的缩放取值的范围，最终夹到一个位置得到结果
 */
public class FindDuplicate_287 {

    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;

        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        //
        int pointer = 0;
        while (nums[pointer] != nums[slow]) {
            slow = nums[slow];
            pointer = nums[pointer];
        }

        return slow;
    }

    // 举实际的 [1,2,2,3,4,5,6] 例子更容易理解这里的二分过程
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
//        int ret = process(nums, n);
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {

            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] <= mid) cnt++;
            }
            if (cnt > mid) {
                // 根据抽屉原理，重复值一定在 [0 ~ mid]
                right = mid;
            } else {
                // 重复值一定在 mid + 1 ~n
                left = mid + 1;
            }
        }
        return left;
    }

//    private int process(int[] nums, int n) {

//        int min = 0, max = 0, equal = 0;
//        int cut = n / 2;
//        while (cut <= n && cut >= 0) {
//            min = 0, max = 0, equal = 0
//            for (int i = 0; i <= n; i++) {
//                if (nums[i] > cut) {
//                    max++;
//                } else if (nums[i] == cut) {
//                    equal++;
//                } else {
//                    min++;
//                }
//            }
//
//            if (equal >= 2) {
//                return cut;
//            }
//            if (max >= min) {
//                // cut 增大
//                cut = cut + cut / 2;
//            } else {
//                // cut 减小
//                cut = cut - cut / 2;
//            }
//        }
//    }
}
