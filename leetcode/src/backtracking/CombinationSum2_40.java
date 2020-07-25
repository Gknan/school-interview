package backtracking;

import java.util.*;

/*
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]


 */
public class CombinationSum2_40 {

    // 先进行排序
    // 使用 set 记录结果 直接去重
    // target 作为参数，每次减去该值
    // 结束条件， target < 0 ；结束遍历，target = 0 ，收集结果
    // 不可重用，所以每次从传入的 idx  开始向后遍历

    // 先写好回溯逻辑的框架，再进行剪枝优化
    // 哪里可以剪枝呢？ 由于我们是从小到大排序的，若 当前数字加入不合法，那么他会面的更大的数字加入一定是不合法的


//    HashSet<LinkedList<Integer>> retSet = new HashSet<>();

    LinkedList<LinkedList<Integer>> retSet = new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 边界处理
        if (candidates.length == 0) return new ArrayList<>();

        Arrays.sort(candidates);

        backtack(candidates, 0, target, new LinkedList<Integer>());

//        return new ArrayList<>(retSet);
        return new ArrayList<>(retSet);
    }

    private void backtack(int[] candidates, int idx, int target, LinkedList<Integer> preList) {

//        if (target < 0) return;
        if (target == 0) {
            // 收集结果
            // 新创建数组并装进结果中
            LinkedList<Integer> list = new LinkedList<>(preList);
            retSet.add(list);
        }
        
        // 根据现在有的列表做选择，现在有的列表是从 cur 开始的所有元素
        for (int i = idx; i < candidates.length; i++) {
            // 尝试剪枝优化
            if (target - candidates[i] < 0) break;

            // 剪枝 2，排序后是重复的数字出现在一起，以如果选中第一个作为解的一部分，那么，后面重复的就不用作为当前解的一部分去向后遍历了，
            // 因为第一个包含的元素更多
            if (i > idx && candidates[i] == candidates[i - 1]) continue;


            preList.add(candidates[i]);

            // 当前选择了第 i 个位置，下一次的可选范围就是 i+1 到后面
            backtack(candidates, i + 1, target - candidates[i], preList);

            preList.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSum2_40 test = new CombinationSum2_40();

        int[] arr = {2,5,2,1,2};
        int target = 5;

        System.out.println(test.combinationSum2(arr, target));
    }
}
