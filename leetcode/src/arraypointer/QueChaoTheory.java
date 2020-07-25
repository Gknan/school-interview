package arraypointer;

import java.util.ArrayList;
import java.util.List;

/*
用于解决给定整数 N 的范围，数组长度也是确定的，找某个特定的元素
下标和下标中的值的对应关系，进行维护，维护完之后，遍历一遍找结果；
还有一种情况是根据数组对应位置的值的 负 号来对当前位置的数字进行某种标记

4，雀巢理论 + 异或运算。
雀巢理论，抽屉原理。
nums 是N个巢，nums 中的值是雀的下表
正确的位置应该是 nums[i] = i + 1; 第 i 个巢 放的雀的编号应该是 i + 1
4.1 把雀放到对应的巢中
4.2 遍历所有，若当前巢中雀不满足关系，说明 i + 1 号雀不在
 */
public class QueChaoTheory {


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
                // 交换后，第 i 个巢 中的雀回到了自己本应该在的位置；但是第 i 个巢中的新的
                // 不雀一定是i 巢中的雀，所以 while再次判断
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
}
