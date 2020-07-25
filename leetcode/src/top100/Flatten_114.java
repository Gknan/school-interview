package top100;

import basics.TreeNode;

/*
给定一个二叉树，原地将它展开为链表。

例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
分析：
连接的顺序是先序遍历的顺序
递归的写法：
先把左子树压平；再把右子树压平；在root 和 root.right 之间插入压平后的左子树

迭代的方式
1，若 root.left == null root= root.right
2，若node 的左孩子不为空，把左孩子添加到node 和 node.right 之间 node 向右指针移动
3，直到遍历完root的右孩子链
因为在每个右链上都对左边做了处理，所以最终会完成
 */
public class Flatten_114 {

    public void flatten2(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 左子树不为空
                TreeNode pre = root.left;
                while (pre.right != null) pre = pre.right;
                // pre 是左子树的最右边节点
                pre.right = root.right;
                root.right = root.left;
                // 断开左孩子连接
                root.left = null;
            }
            // update root
            root = root.right;
        }
    }
    public void flatten(TreeNode root) {

        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        TreeNode leftRoot = root.left;
        if (leftRoot != null) {
            TreeNode leftLastNode = findLast(leftRoot);
            root.left = null;
            leftLastNode.right = root.right;
            root.right = leftRoot;
        }

    }

    // 找到最右边的节点
    private TreeNode findLast(TreeNode root) {
        if (root == null) return null;

        while (root.right != null) {
            root = root.right;
        }

        return root;
    }
}
