package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。
示例:
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

分析：
1，先对数组进行排序
2，同理，这里也是子集，没有顺序之分，所以采用下次选择时 范围缩小的策略
3，每一到一个节点，进行结果记录
4，重复剪枝 nums[i] == nums[i - 1] 因为 i - 1 的链更长，一定包含了 i 的链

 */
public class SubsetsWithDup_90 {

    List<List<Integer>> retList;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        retList = new ArrayList<>();
        if (nums.length < 1) return retList;

        Arrays.sort(nums);

        backtrack(nums, 0, new LinkedList<Integer>());

        return retList;
    }


    private void backtrack(int[] nums, int idx, LinkedList<Integer> preList) {
        // 统计结果
        retList.add(new ArrayList<>(preList));

        // 达到最终最终状态
        if (idx == nums.length) return;

        for (int begin = idx; begin < nums.length; begin++) {

            // 剪枝
//            if (begin > idx && nums[begin] == nums[begin - 1]) break;
            if (begin > idx && nums[begin] == nums[begin - 1]) continue;

            preList.add(nums[begin]);
            backtrack(nums, begin + 1, preList);
            preList.removeLast();
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup_90 test = new SubsetsWithDup_90();
        int[] nums = {1,2,2};

        System.out.println(test.subsetsWithDup(nums));
    }
}
