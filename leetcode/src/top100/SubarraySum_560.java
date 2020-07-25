package top100;

import java.util.Arrays;
import java.util.HashMap;

/*
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

分析：
1、排序后 两重 for 解决 时间复杂度 O(n2)
2、写成递归版本

上面理解题目错误。因为这里的解是跟数组的序列相关的，所以不能进行排序
若不能排序，每次都需要找到结尾才可以
前缀和：
sum[i] 表示0~i 位置的和； sum[j] 标识0~j 位置的和，若存在 sum[i] - sum[j] = k ，则找到一组和为 k 的连续子数组
使用 map 存在 sum: cnt 出现的次数
遍历过程中，更新 map，当前 sum - k 若存在于 map中，说明当前元素有连续和，统计结果

 */
public class SubarraySum_560 {

    public int subarraySum4(int[] nums, int k) {

        if (nums.length < 1) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        // 根据定义 若k =从0开始的连续和，那么 sum[i] - sum[-1] 才是结果，所以这里的 初始值相当于添加了 0:1 的次数级 sump-1]
        map.put(0, 1);// 初始添加和为0 的连续子数组个数是1

        int ret = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                ret += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ret;
    }

    public int subarraySum3(int[] nums, int k) {

        if (nums.length < 1) return 0;

        int ret = 0;

        for (int i = 0; i < nums.length; i++) {
            int curCnt = 0;
            int curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j];
                if (curSum == k) curCnt ++;
            }
            ret += curCnt;
        }

        return ret;
    }

    // 排序后结果错误
    public int subarraySum2(int[] nums, int k) {

        if (nums.length < 1) return 0;

        Arrays.sort(nums);
        int ret = 0;

        for (int i = 0; i < nums.length && nums[i] <= k; i++) {
            int curCnt = 0;
            int curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j];
                if (curSum == k) curCnt ++;
                else if (curSum > k) break;
            }
            ret += curCnt;
        }

        return ret;
    }

    int ret = 0;
    public int subarraySum(int[] nums, int k) {

        if (nums.length < 1) return 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            help(nums, i, k);
        }

        return ret;
    }

    private void help(int[] nums, int start, int k) {
        if (k < 0) return;
        if (k == 0) ret ++; // 若是 0 的话，就会不同的计数

        help(nums, start + 1, k - nums[start]);
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;

        System.out.println(new SubarraySum_560().subarraySum3(nums, k));
        System.out.println(new SubarraySum_560().subarraySum4(nums, k));
    }
}
