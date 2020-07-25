package backtracking;

import java.util.*;

/*
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]


 */
public class CombinationSum3_216 {


    List<List<Integer>> retList;
//    boolean[] visited;// 因为我们是遍历是按照 每次想后一个位置的，所以不需要 visited
    public List<List<Integer>> combinationSum3(int k, int n) {
        retList = new ArrayList<>();
        // 剪枝，因为所有数字不能重复，所以最多选择 9 个；n 的值不能大于 45
        if (k > 9 || n > 45) return retList;

        //
//        visited = new boolean[10];
//        dfs(k, n, 0, 0, new LinkedList<Integer>());

        // 这里是深度优先，所有需要栈保存，这里使用双向链表作为栈
        LinkedList<Integer> stack = new LinkedList<>();


        dfs(k, n, 1, stack);

//        dfs(k, n, 1, stack);

        return retList;
    }

    /**
     *
     * @param remCnt 剩余的数字个数
     * @param remain 剩余的
     * @param start 当前开始的下标，下一层递归从 start + 1开始，就可以避免重复
     * @param stack 截止目前走过的路径
     */
    private void dfs(int remCnt, int remain, int start, LinkedList<Integer> stack) {
        // 若 当前的 remain 超过 返回
        if (remain < 0) return;

        if (remCnt == 0) {
            if (remain == 0) {
                // 满足两个条件，收集结果，并返回
                retList.add(new ArrayList<>(stack));
            }
            return;
        }

        // remain > 0 且 remCnt > 0 从 start  开始遍历 遍历到 9
        for (int cur = start; cur <= 9; cur++) {
            // 剪枝
            if (remain - cur < 0) break;


            // 加入路径
            // 注意，这里使用的是来加入路径，不是 start
            stack.addLast(cur);
            // 递归往下走一层
            // remCnt 减少一次，remain 减去当前选的路径，start 从 cur + 1 开始
            dfs(remCnt - 1, remain - cur, cur + 1, stack);

            // 递归结束，还原状态
            stack.removeLast();
        }
    }

//    private void dfs(int cnt, int remain, int start, Deque<Integer> preList) {
//
////        if (preSum == n && preList.size() == k) {
////            retList.add(new ArrayList<>(preList));
////            return;
////        };
////        if (remain < 0) return;
////        if (cnt == 0) {
////            if (remain == 0) {
////                // 收集结果
////                retList.add(new ArrayList<>(preList));
////                // 这里要加 return 结束循环
////
////                return;
////            }
////
////            return;// 结束循环
////        }
//
//
//        for (int i = start; i <= 9; i++) {
//            // 加入
////            preSum += i;
//            preList.addLast(i);
////            visited[i] = true;
////            dfs(cnt - 1, remain - i, start + 1, preList);
//            dfs(cnt - 1, remain - i, i + 1, preList);
////            visited[i] = false;
//            preList.removeLast();
//        }
//    }

    public static void main(String[] args) {
        CombinationSum3_216 test = new CombinationSum3_216();
        System.out.println(test.combinationSum3(3, 7));
    }
}
