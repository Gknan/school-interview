package top100;

import basics.TreeNode;

/*
给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

例如：

输入: 二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13

分析：
对于 BST，右子树的所有节点均大于根节点；
递归的方法，先转换以左子树为根的 BST，再转换右子树；最后计算根节点并返回
左子树所有节点 小于 根节点；右子树所有节点大于根节点
所以先处理右子树；再处理根节点；最后处理左子树
逆中序遍历，先右 根 再左；遍历过程中记录 sum，根据 sum 更新 node.val
空间复杂度 O(h)

moris遍历
若当前节点的左子树不空，找到前序 pre，pre.next = cur; cur = cur.left;
若当前节点的左子树为空，处理 cur，cur= cur.right
若当前节点的前序指向自己，pre.next = null; cur = cur.right;

改写 moris 解决累加和
moris 中的 left 和right 全部调换吗? 就变成了 逆中序 不正确
正要的是该节点的访问时机


 */
public class ConvertBST_538 {

    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {

        if (root == null) {
            return null;
        }

        convertBST(root.right);

        sum += root.val;
        root.val = sum;

        convertBST(root.left);

        return root;

    }

    public static TreeNode morisTransBack(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (root != null) {
            if (root.right != null) {
                TreeNode succ = getSuccessor(root);
                if (succ.left == null) {
                    succ.left = root;
                    root = root.right;
                } else {
                    succ.left = null;
//                    System.out.println(root.val);
                    sum += root.val;
                    root.val = sum;
                    root = root.left;
                }
            } else {
//                System.out.println(root.val);
                sum += root.val;
                root.val = sum;
                root = root.left;
            }
        }

        return node;
    }

    private static TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node;
        if (node != null) {
            if (node.right != null) {
                succ = node.right;
                while (succ.left != null && succ.left != node) {
                    succ = succ.left;
                }
            }
        }

        return succ;
    }

    public static void morisTrans(TreeNode root) {

        while (root != null) {
            if (root.left != null) {
                TreeNode pre = getPrecessor(root);
                if (pre.right == null) {
                    pre.right = root;
                    root = root.left;
                } else {
                    pre.right = null;
                    System.out.println(root.val);
                    root = root.right;
                }
            } else {
                System.out.println(root.val);
                root = root.right;
            }
        }
    }


    private static TreeNode getPrecessor(TreeNode node) {
        TreeNode pre = node;
        if (node != null) {
            if (node.left != null) {
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
            }
        }

        return pre;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(-4);
        TreeNode node5 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;


//        morisTrans(node1);

//        System.out.println("=============");
        TreeNode node = morisTransBack(node1);
        System.out.println(node.val);

    }
}
