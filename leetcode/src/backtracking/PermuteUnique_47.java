package backtracking;

/*
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

全排列，要求每个数字都选中，不同的是出现的位置不同
有重复数字，先进行排序；排序后就可以剪枝

状态
选择 当前没选过的元素   为了方便管理重复元素的可选和已选，当前选择的列表中存储下标  Map<下标：下标对应的值>
终止条件 已经选完所有元素


剪枝条件：刚刚撤销的数字如果跟自己是一样的，且当一个位置和自己一样，剪枝；怎么表达刚刚插销的消息呢？
boolean [] 数组保存 已经访问的信息；而不是用 set，因为 set 不能看到刚刚撤销的信息


 */
import java.util.*;

public class PermuteUnique_47 {

    List<List<Integer>> ret;
    public List<List<Integer>> permuteUnique(int[] nums) {
        ret = new ArrayList<>();
        if (nums.length < 1) return ret;


        Arrays.sort(nums);
        
//        backtrack(nums, 0, new HashMap<Integer, Integer>());
        LinkedList<Integer> preList = new LinkedList<>();

//        HashSet<Integer> visited = new HashSet<>();// 存放访问过的下标

        boolean[] visited = new boolean[nums.length];

        backtrack(nums, preList, visited);
//        HashMap<Integer, Integer> idx2val = new HashMap<>();

        // map key 排序导致我们对视了顺序的信息，还是使用 List 存放过程中的结果，set 存放已经访问过的下标
//        for (int i = 0; i < nums.length; i++) {
//            if (i > 0 && nums[i] == nums[i - 1]) continue;
//            // 第一次选中，向下i走
//            visited.add(i);
//            preList.add(nums[i]);
//            backtrack(nums, preList, visited);
//            preList.removeLast();
//            visited.remove(i);
////            idx2val.put(i, nums[i]);
////            backtrack(nums, idx2val);
////            idx2val.remove(i);
//        }
        return ret;
    }

    private void backtrack(int[] nums, LinkedList<Integer> preList,  boolean[] visited) {

        if (preList.size() == nums.length) {
            // 收集结果 key 是 下标，values 才是对应的值
//            System.out.println(idx2val);
            ret.add(new ArrayList<>(preList));

            return;
        }
        
        // 做选择
        for (int begin = 0; begin < nums.length; begin++) {

            // 若已经选过某位置的节点，跳过
            if (visited[begin]) continue;

            // 重复元素的取出
            if (begin > 0 && nums[begin] == nums[begin - 1] && visited[begin - 1] == false) continue;

            // 选中没选过的节点，记录
            visited[begin] = true;
            preList.add(nums[begin]);
            backtrack(nums, preList, visited);
            preList.removeLast();
            visited[begin] = false;
        }
    }

    public static void main(String[] args) {
        PermuteUnique_47 test = new PermuteUnique_47();

        int[] nums = {1,1,2};
        System.out.println(test.permuteUnique(nums));

//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(2, 1);
//        map.put(1, 2);
//
//        // 说明 HashMap 对 记录按照 key 的顺序排了序，导致我们丢失了信息
//        System.out.println(map);
    }
}
