package mustlearn;

import java.util.LinkedList;
import java.util.List;

/*
回溯框架总结：
解决一个回溯问题，实际上就是一个决策树的遍历过程
只需要考虑三个问题：
1，路径：已经做出的选择
2，选择列表：当前可以做的选择集合
3，结束条件：到达决策树底层，无法在做选择的条件

代码框架：
result = []
def backtrack(路径, 选择列表)
    if 满足结束条件：
        result.add(路径)
        return

    for 选择 in 选择列表：
        做选择
        backtrack(路径, 选择列表)
        撤销选择

核心的是 for 循环里的递归，递归调用之前做选择，在递归调用之后撤销选择


回溯算法的一个特点，没有重叠子问题，纯暴力穷举所有情况，复杂度一般很高；
全排列问题这里的复杂度不会低于 N!




 */
public class BackTrack {

    static List<LinkedList<Integer>> ret;
    public static List<LinkedList<Integer>> quanpailie(int[] arr) {
        ret = new LinkedList<>();

        if (arr == null) return ret;


        backAllPailie(arr, new LinkedList<Integer>());
        return ret;
    }

//    // 路径 list，选择列表 arr[i,...] 写错了
//    private static void backAllPailie(int[] arr,
//                                      int idx, LinkedList<Integer> preList) {
//        // 终止
//        if (idx == arr.length) {
//            ret.add(preList);
//        }
//
//        for (int i = idx; i < arr.length; i ++) {
//            preList.add(arr[i]);
//            backAllPailie(arr, i, preList);
//        }
//    }

    // 路径 list，选择列表 arr[i,...]
    private static void backAllPailie(int[] arr,
                                      LinkedList<Integer> preList) {
        // 终止
        if (preList.size() == arr.length) {
            ret.add(preList);
        }

        for (int i = 0; i < arr.length; i ++) {
            // 排除已经做过的选择
            if (preList.contains(arr[i])) continue;
            preList.add(arr[i]);
            backAllPailie(arr, preList);
            preList.removeLast();
        }
    }
}
