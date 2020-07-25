package top100;


/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
示例 1:

输入: [2,2,1]
输出: 1

示例 2:

输入: [4,1,2,1,2]
输出: 4

分析：
方法1：
位运算：
两个相同的数字亦或的结果是0
0 ^ x = x
由于是非空整数数组，所以从第一个数字开始异或

方法2：
借助结合，遍历数组，若第一次出现，添加到集合中，若第二次出现，从结合中删除；最后
集合中剩下的元素就是结果

方法3：
数学法；2∗(a+b+c)−(a+a+b+b+c)=c

方法4
排序

 */
public class SingleNumber_136 {

    public static int singleNumber(int[] nums) {
        int ret = nums[0];

        for (int i = 1; i < nums.length; i ++) {
            ret ^= nums[i];
        }

        return ret;
    }

    public static void main(String[] args) {
        int [] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }
}
