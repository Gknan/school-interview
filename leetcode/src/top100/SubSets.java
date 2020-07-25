package top100;

import java.util.ArrayList;
import java.util.List;

/*
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

分析：
看成是选位置，第一个位置可以选，也可以不选；然后进入第二个状态，第二个位置可以选，也可以不选

顺序选择：对于每个元素，从考虑向其中添加它后面的元素
 */
public class SubSets {

    // 使用递归的思想，如开始是 []; 每次遍历到一个元素，把钙元素加入到之前每个子集中，遍历到1，得到[] [1]; 遍历到2，得到[] [1] [2] [1, 2]
    public static List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        if (nums == null || nums.length < 1) return ret;
        ret.add(new ArrayList<>());

        for (int i: nums) {
            //
            List<List<Integer>> subSets = new ArrayList<>();

            // 遍历之前的每个集合添加新元素
            for (List<Integer> list: ret) {
//                list.add(i);
//                subSets.add(list);
                List<Integer> newList = new ArrayList<Integer>(list);
                newList.add(i);
                subSets.add(newList);
            }

            // 得到的新集合加入到结果中
            for (List<Integer> ele: subSets) {
                ret.add(ele);
            }
        }

        return ret;
    }

    // 时间复杂度 O(N*2^N)
    // 位运算 000 表示没有选择 001 表示选择 0 号位置
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        if (nums == null || nums.length < 1) return ret;

        for (int i = 0; i < (1 << (nums.length)); i++) {
            // i 是对应的一种排列
            ArrayList<Integer> sublist = new ArrayList<>();
            for (int j = 0; j < nums.length; j ++) {
                if (((i >> j) & 1) == 1) {
//                    sublist.add(j);
                    sublist.add(nums[j]);
                }
            }
            ret.add(sublist);
        }

        return ret;
    }

    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        if (nums == null || nums.length < 1) return ret;

        processInOrder(nums, 0, ret, new ArrayList<>());

        return ret;
    }

    private static void processInOrder(int[] nums, int curIdx, List<List<Integer>> ret, ArrayList<Integer> pre) {
//        if (curIdx > nums.length) return;
        ret.add(new ArrayList<>(pre));

        for (int i = curIdx; i < nums.length; i ++) {
            pre.add(nums[i]);
//            processInOrder(nums, curIdx + 1, ret, pre);
            processInOrder(nums, i + 1, ret, pre);
            pre.remove(pre.size() - 1);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        if (nums == null || nums.length < 1) return ret;

        process(nums, 0, ret, new ArrayList<Integer>());

        return ret;
    }

    private static void process(int[] nums, int curIdx, List<List<Integer>> ret, ArrayList<Integer> pre) {
        if (curIdx == nums.length) {
            ret.add((ArrayList)pre.clone());
            return;
        }

        process(nums, curIdx + 1, ret, pre);

        pre.add(nums[curIdx]);
        process(nums, curIdx + 1, ret, pre);
//        pre.remove(nums[curIdx]);
        pre.remove(pre.size() - 1); // 这里移除的应该是队列中的最后一个元素，因为添加的元素在队列的最后


    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets4(nums));
    }
}
