package top100;

import basics.TreeNode;
import basics.TreeOperation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两
个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

递归版本：
按照是否偷根将偷窃分成两个方案，偷根，和不偷根

讲递归的画成树结构，发现存在着重复计算，为了消除重复计算，我们使用 mem 备忘录 mem 中保存的是以当前节点为根的树偷得的最大值


备忘录改递推

递推怎么改 DP
dp[0] : 标识不偷当前点能得到的最大收益
dp[1]：表示偷当前的点能得到的最大收益
dp[0] = Math.max(root.leftdp[0], root.left[1]) +Math.max(root.rightdp[0] ,root.rightdp[1]); 不偷根的
的最大收益取决于 左子树的最大收益 + 右子树的最大收益

dp[1] = root.leftdp[0] + root.rightdp[0] 投了根的最大收益取决于不偷左子树的最大收益 + 不偷右子树的最大收益


 */
public class Rob_337 {

    // 偷盗 root 所在的小区得到的最大值
    public static int rob(TreeNode root) {

        if (root == null) return 0;

        // 方案1 偷根
        int retPlan1 = 0;
        retPlan1 += root.val; // 偷根
        // 偷孙子
        retPlan1 += (root.left != null) ? rob(root.left.left) + rob(root.left.right) : 0;
        retPlan1 += (root.right != null) ? rob(root.right.left) + rob(root.right.right) : 0;

        // 方案2 不偷根
        int retPlan2 = 0;
        retPlan2 += rob(root.left) + rob(root.right);

        return Math.max(retPlan1, retPlan2);
    }

    static HashMap<TreeNode, Integer> mem= new HashMap<>();
    // 递归 + 备忘录
    public static int rob2(TreeNode root) {
        if (root == null) return 0;

        if (mem.containsKey(root)) return mem.get(root);

        // 方案1
        int retPlan1 = 0;
        retPlan1 += root.val; // 偷根
        // 偷孙子
        retPlan1 += (root.left != null) ? rob(root.left.left) + rob(root.left.right) : 0;
        retPlan1 += (root.right != null) ? rob(root.right.left) + rob(root.right.right) : 0;

        // 方案2 不偷根
        int retPlan2 = 0;
        retPlan2 += rob(root.left) + rob(root.right);

        // update mem
        mem.put(root, Math.max(retPlan1, retPlan2));

        return mem.get(root);
    }

    // 递归转为 递推 按层次计算每一层的节点的 rob 值，然后更新上一层 所以借助栈和队列，队列来层次遍历，栈用来保存层次遍历的结果，方法还原
    public static int rob3(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        HashMap<TreeNode, Integer> node2Val = new HashMap<>();

        // 为了减少空值的判断 HashMap 支持 null 值作为 key 先把 null 加进去
        node2Val.put(null, 0);

        // 层次遍历二叉树，加入到 stack 中
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top == null) continue;

            stack.push(top); // stack 中是按照每一行从右往左弹出的

            queue.add(top.left);
            queue.add(top.right);
        }

        // 从栈中按照行弹出，更新 node2Val
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();

            if (top.left == null && top.right == null) {
                // 当前是最后一层节点 能偷的最大值就是节点的值
                node2Val.put(top, top.val);
//                continue;
            } else if (top.left == null) {
                // 左孩子为空，右孩子不是空
                // 偷当前
                int plan1 = top.val + node2Val.get(top.right.right) + node2Val.get(top.right.left);

                // 不偷当前
                int plan2 = node2Val.get(top.right);
                int value = Math.max(plan1, plan2);
                node2Val.put(top, value);
            } else if (top.right == null) {
                // 右孩子为空，左孩子不是空
                // 偷当前
                int plan1 = top.val + node2Val.get(top.left.right) + node2Val.get(top.left.left);

                // 不偷当前
                int plan2 = node2Val.get(top.left);
                int value = Math.max(plan1, plan2);
                node2Val.put(top, value);
            } else {
                // 偷当前
                // 左右孩子不为空
                int plan1 = top.val + node2Val.get(top.left.right) + node2Val.get(top.left.left)
                        + node2Val.get(top.right.right) + node2Val.get(top.right.left);

                // 不偷当前
                int plan2 = node2Val.get(top.left) + node2Val.get(top.right);
                int value = Math.max(plan1, plan2);
                node2Val.put(top, value);
            }
        }

        return node2Val.get(root);
    }


    // DP 法 时间 O(N) 因为每个节点只到达了一次 空间 栈深度最差 O（N）
    public static int rob4(TreeNode root) {
        if (root == null) return 0;

        int[] ret = toRob(root);

        return Math.max(ret[0], ret[1]);
    }


    // 从 node 开始工作，返回二维数组 arr[0] 表示不偷node当前的最大收益 arr[1] 偷node 的最大收益
    private static int[] toRob(TreeNode node) {

        if (node == null) return new int[]{0, 0};

        int[] left = toRob(node.left);
        int[] right = toRob(node.right);

        int[] ret = new int[2];
        // norob
        ret[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
//        ret[1] = left[0] + right[0];
        // rob
        ret[1] = left[0] + right[0] + node.val;

        return ret;
    }

    public static void main(String[] args) {
//
//        TreeNode node1 = new TreeNode(3);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(1);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.right = node4;
//        node3.right = node5;


        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

//        TreeOperation.show(node1);

        System.out.println(rob(node1));
        System.out.println(rob2(node1));
        System.out.println(rob3(node1));
        System.out.println(rob4(node1));
    }
}
