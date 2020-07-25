package top100;

import basics.TreeNode;

/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:
给定二叉树 [1,2,2,3,3,null,null,4,4]
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。

分析：

每个节点都要满足

递归的写法，

 */
public class IsBalanced_110 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftLen = process(root.left);
        int rightLen = process(root.right);

        // 当前节点为根满足，左子树满足，右子树满足
        return Math.abs(leftLen - rightLen) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 返回当前为 根的树高
     * @param node
     * @return
     */
    private int process(TreeNode node) {
        if (node == null) return 0;

        int leftlen = process(node.left);
        int rightLen = process(node.right);

        return Math.max(leftlen, rightLen) + 1;
    }
}
