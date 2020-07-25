package top100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

分析：
1，第一个位置可以选择 1,2， 3
2，第二个位置根据第一个位置来选择，由于选择的时候之前选过的位置不能选，所以用一个集合来放已经选过的元素
3，递归终止位置，已经选过的元素个数为nums的长度

回溯框架
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
关键在于先画出回溯对应的虚拟存在的树，然后根据树的深度遍历得到结果
 */
public class Permute_46 {

    public static List<List<Integer>> ret = new ArrayList<>();

    // 不借助 visited，使用交换的方式
    public static List<List<Integer>> permute2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList();
        }

        ArrayList<Integer> nums_list = new ArrayList<>();
        for (int num: nums) {
            nums_list.add(num);
        }

        int n = nums.length;
        backtrack(n, nums_list, 0);
        return ret;
    }

    private static void backtrack(int n, ArrayList<Integer> nums_list, int cur) {
        if (cur == n) {
            ret.add(new ArrayList<>(nums_list));
        }

        /*
        面的回溯法, 只不过是将第n个位置的选择使用交换表示出来罢了,
         比如说第一层计算,第一个位置可选1,2,3, 上面的算法就是通过交换,
         将1,2,3分别放在了第一个位置上, 第二个位置可选的元素是除了第
         一个位置的元素以外的其他所有元素, 则第二层做的事是将每个第一层计算
         来的结果第一个位置不变, 第二个位置通过交换获取到所有的N-1可选元素,
         一直到第n层,就获取到了每个位置的所有可能性了, 本质上还是排列组合的,
         */
        for (int i = cur; i < n; i ++) {// 第一次交换后，相当于后面所有元素都有机会在第一个位置
            Collections.swap(nums_list, cur, i);
            backtrack(n, nums_list, cur + 1);
            Collections.swap(nums_list, cur, i);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList();
        }

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        process(nums, set, list);
//        for (int i = 0; i < nums.length; i ++) {
//            if (!set.contains(nums[i])) {
//                set.add(nums[i]);
//                list.add(nums[i]);
//                process(nums, set, i, list);
//                set.remove(nums[i]);
//                list.remove(list.size() - 1);
//            }
//        }

        return ret;
    }

    private static void process(int[] nums, HashSet<Integer> set, ArrayList<Integer> list) {
        if (set.size() == nums.length) {
            ret.add(new ArrayList(list));
            return;
        }

        for (int i = 0; i < nums.length; i ++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                list.add(nums[i]);
                process(nums, set, list);
                set.remove(nums[i]);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
