package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

分析：
n = 4 k = 2
1 2 3 4   的所有可能的两个数字的组合

注意的是，组合的意思 [1, 2] 和 [2, 1] 表示的是同一个组合
组合就相当于去重？

每次从缩小选择的范围，后面选的一定比前面的值大

 */
public class Combine_77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> retList = new ArrayList<>();
        if (n < k) return retList;

        backtrack(n, k, 1, retList, new LinkedList<Integer>());

        return retList;
    }

    private void backtrack(int n, int remCnt, int idx,
                           List<List<Integer>> retList, LinkedList<Integer> preList) {
        // 结束条件
        if (remCnt == 0) {
            // 收集结果
            retList.add(new ArrayList<>(preList));
            return;
        }

        // 选择范围内做选择

        for (int begin = idx; begin <= n; begin++) {
            preList.add(begin);

//            backtrack(n, -- remCnt, ++ begin, retList, preList);
            backtrack(n, remCnt - 1, begin + 1, retList, preList);

            preList.removeLast();
        }
    }

    public static void main(String[] args) {
        Combine_77 test = new Combine_77();

        System.out.println(test.combine(4, 2));
    }
}
