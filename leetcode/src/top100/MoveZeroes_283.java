package top100;

/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
示例:
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]

说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

分析：
1，暴力法，遍历遇到 非 0 元素，向前移动到前部分元素的最后一个非零元素之后；
时间复杂度，O（N^2） 空间复杂度 O(1)
2，统计0的个数 cnt—_0 ；指针p 指向前半段中非零部分的最后一个元素；i 是遍历的当前元素的下标，当i >= cnt_0时，后面的位置设置为0；否则，设置为 arr[i]
3，第二种做法对于 [0,0,0,....,0,1] 这种类型，写的次数太多 更加优化的做法是：lastIndex 满指针，指向非零的下一个；cur 快指针，指向当前；
如果arr[cur] 不是零的话，那么 cur 位置的值就是cur 后面下标的某个 0 或者非零值。所以 cur 相当于指向了第一个不等于0的位置，cur 和 lastIndex 之间都是0，
所以 arr[cur] 不等于 0 的话，swap(arr[cur], arr[lastIndex]) ，然后两个下标都向下移动；若 arr[cur] 等于 0 ，只移动 cur 指针即可
 */

import java.util.Arrays;

public class MoveZeroes_283 {

    public static int[] moveZeroes2(int[] nums) {

        for (int lastIdx = 0, cur = 0; cur < nums.length; cur ++) {
            if (nums[cur] != 0) {
//                swap(nums[lastIdx], nums[cur]);
                int temp = nums[lastIdx];
                nums[lastIdx] = nums[cur];
                nums[cur] = temp;

                lastIdx++;
            }
        }

        return nums;

    }


    public static int[] moveZeroes(int[] nums) {

        int q = 0; // non-0 last index next
//        int cnt = count0(nums);

        for (int i = 0; i < nums.length; i ++) {
//            if (i <= cnt - 1) {
//                nums[++ q] = nums[i];
//            }
            if (nums[i] != 0) {
                nums[q ++] = nums[i];
            }
        }

        while (q < nums.length) {
            nums[q ++] = 0;
        }

//        System.out.println("cnt of 0s: " + cnt);

        return nums;
    }

    // count the number of 0 in nums
    private static int count0(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) {
                cnt ++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};

        System.out.println(Arrays.toString(moveZeroes2(nums)));
    }
}
