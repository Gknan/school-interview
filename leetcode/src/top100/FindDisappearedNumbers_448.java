package top100;

/*

给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只
出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]

分析：
1，数组 map，大小为 n，遍历 nums ，填充 map 下标对应的数组值；遍历 map ，找到结果。时间 O(N) 空间 O(N)
2，排序后，就可以找到没有出现在的数字 时间 O(N2) 空间 O(1)
3，指针法，遍历数组对于 i 下标，把 nums[abs(nums[i]) - 1] 置为负数。然后遍历一遍数组，
如果当前元素是负数，说明我们在数组中存在 i + 1。实际上是利用负号来记录已经出现过。

4，雀巢理论 + 异或运算。
雀巢理论，抽屉原理。
nums 是N个巢，nums 中的值是雀的下表
正确的位置应该是 nums[i] = i + 1; 第 i 个巢 放的雀的编号应该是 i + 1
4.1 把雀放到对应的巢中
4.2 遍历所有，若当前巢中雀不满足关系，说明 i + 1 号雀不在
 */

import java.util.List;
import java.util.ArrayList;

public class FindDisappearedNumbers_448 {

    private static void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }

        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    public static List<Integer> findDisappearedNumbers2(int[] nums) {

        for (int i = 0; i < nums.length; i ++) {
            while (nums[i] != nums[nums[i] - 1]) { // 若现在第 i 个巢的雀 与
                // 第 i 个巢现在的雀所本应该在的巢中的雀不是同一个，交换 第 i 个巢 和
                // 现在i巢中雀的归属位置
                // 交换后，第 i 个巢 中的雀回到了自己本应该在的位置；但是第 i 个巢中的新的雀不一定是i 巢中的雀，所以 while再次判断
                // 知道 while 结束，要么 i 的巢中找到了自己的雀，要么i巢中的雀是个重复的雀
                swap(nums, i, nums[i] - 1);
            }
        }

        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != i + 1) {
                ret.add(i + 1);
            }
        }

        return ret;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < nums.length; i ++) {
            if (nums[Math.abs(nums[i]) - 1] > 0) {
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] > 0) {
                // 说明 i + 1 没有出现过
                ret.add(i + 1);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers2(nums));
    }
}
