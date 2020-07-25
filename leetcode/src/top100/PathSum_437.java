package top100;

import basics.TreeNode;

/*
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
返回 3。和等于 8 的路径有:
1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11

分析：
1，递归的方法
DPS，遍历; 注意分成两种情况，一种是包括根的递归；一种是不包括根的递归；处理方式不同。

2，连续节点的求和，容易联想到数组中的连续和问题。对于数组 arr[n]，使用 sums[n + 1] b保存
  0 ~n 的和，则 i~j 节点的和为 sums[j] - sums[i]

3，每当遍历到一个节点，该节点的值加到 pathSum 中，map <pathSum， cnt> map 中保存遍历到当前节点时，
，路径上出现的 pathSum - sum 的个数；若 pathSum - sum 存在于 map 中，则说明当前的 n 条路径减去
之前的前 n 条路径，结果为 sum，那么 统计到 map.get(pathSum) 条路径

核心优化思想都是：
这道题用到了一个概念，叫前缀和。就是到达当前元素的路径上，之前所有元素的和。
计算以当前节点为路径终点的所有路径和。 关键点：用一个数组保存从根节点到当前节点路径
 */

import java.util.Map;
import java.util.HashMap;


public class PathSum_437 {

    private static int[] sums;
    private static int cnt = 0;


    public static int pathSum3(TreeNode root, int sum) {

        Map<Integer, Integer> map = new HashMap<>();

        // 截止目前，前 0 项中路径和 为 0 出现了 1 次；若 只要一个节点，且 sum == preSum
        map.put(0, 1);

        return process(root, sum, 0, map);

    }

    private static int process(TreeNode root, int sum,
                               int pathSum, Map<Integer, Integer> map) {

        if (root == null)
            return 0;

        int res = 0;
        pathSum += root.val;

        // 找是否存在前k 项和是 当前路径和 减去 sum，若存在，则 更新 res
//        res += map.getOrDefault(pathSum - sum, 0) + 1;
        // 相当于找存在多少个 pathSum - sum 的路径和 这里是向上整理
        res += map.getOrDefault(pathSum - sum, 0);

        // 截止当前节点的路径和加入到 map 中
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);

        // 这里导致了 中间节点为根节点的路径和的引入
        // 这里是向下扩展
        res += process(root.left, sum, pathSum, map)
                + process(root.right, sum, pathSum, map);

        // 恢复现场 因为前面添加了 map.put(pathSum, xx) 所以这里恢复之后最小为 0 方便回溯
        map.put(pathSum, map.get(pathSum) - 1);
        return res;
    }

    public static int pathSum2(TreeNode root, int sum) {
        if (root == null)
            return 0;

        sums = new int[maxDepth(root) + 1];
        helper(root, 1, sum);
        return cnt;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    private static void helper(TreeNode node, int level, int sum) {
        if (node == null)
            return;

        sums[level] = sums[level - 1] + node.val;
        for (int i = 0; i < level; i ++) {
            if (sums[level] - sums[i] == sum)
                cnt ++;
        }

        helper(node.left, level + 1, sum);
        helper(node.right, level + 1, sum);
    }

    // 计算以 root 作为根节点的树种 路径和 为 sum 的路径树，并返回
    public static int pathSum(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }

        // include root root in ans path
//        int includeRootCnt = pathSum(root.left, sum - root.val) + pathSum(root.right, sum - root.val);
        int includeRootCnt = dfs(root, sum);

        // notclude root root not in ans path
        int notIncludeRootCnt = pathSum(root.left, sum) + pathSum(root.right, sum);


        return includeRootCnt + notIncludeRootCnt;
    }

    public static int dfs(TreeNode root, int sum) {
        int ret = 0;
        if (root == null) {
            return ret;
        }

        if (sum == root.val) {
            ret ++;
        }

        return ret + dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
    }

    public static void main(String[] args) {

        // root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(11);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(-2);
        TreeNode node9 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node5.right = node9;


        System.out.println(pathSum(node1, 8));
        System.out.println(pathSum2(node1, 8));
        System.out.println(pathSum3(node1, 8));
    }
}
