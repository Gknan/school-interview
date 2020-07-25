package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。

示例 1:

输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
示例 2:

输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

回溯法：
target - pre
pre 是已选元素的总和，
退出条件，target - pre < 0 返回 target -pre == 0 收集结果
每次新的循环都从上一次传递来的位置开始向后

时间复杂度：
剪枝优化：先对原来的数组排序，排序后可以剪枝？不可以剪枝

动态规划求解：从1开始，看1 的组成；target 的组成一定是小于target的组成加上存在于 candidates 中的数字；

 */
public class CombinationSum_39 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return ret;

        // 排序为了剪枝
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, ret, new ArrayList<Integer>());

        return ret;
    }

    private static void backtrack(int[] nums, int index, int target, List<List<Integer>> ret, ArrayList<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ret.add(new ArrayList<>(list));
        }

        for (int i = index; i < nums.length; i ++) {
            // 剪枝
            if (target - nums[i] < 0) break;

            list.add(nums[i]);
            backtrack(nums, i, target - nums[i], ret, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,3,5};
        int target = 8;

        System.out.println(combinationSum(nums, target));
    }
}
