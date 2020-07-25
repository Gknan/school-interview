package top100;

import basics.TreeNode;
import basics.TreeOperation;

/*
BST 树 转为 双向链表
思路：
中序遍历，过程中修改指针

 */
public class BSTToDList426 {

    public TreeNode bstToDlist(TreeNode root) {

        if (root == null) return root;
        TreeNode[] dList = inorderProcess(root);
        // 维护循环指针
//        dList[1].right = dList[0];
//        dList[0].left = dList[1];

        return dList[0];
    }

    // node 为根的BST 转为双向链表 返回 双向链表的head 后序遍历解决
    private TreeNode[] inorderProcess(TreeNode node) {
        if (node == null) return new TreeNode[]{null, null};

        TreeNode[] left = inorderProcess(node.left);
        TreeNode[] right = inorderProcess(node.right);

        TreeNode leftHead = left[0];
        TreeNode leftTail = left[1];
        TreeNode rightHead = right[0];
        TreeNode rightTail = right[1];

        TreeNode head = null;
        TreeNode tail = null;
        if (leftHead != null) {
            head = leftHead;
            // 维护指针
            node.left = leftTail;
            leftTail.right = node;
        } else {
            head = node;
        }

        if (rightHead != null) {
            node.right = rightHead;
            rightHead.left = node;
            tail = rightTail;
        } else {
            tail = node;
        }

        return new TreeNode[]{head, tail};
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

//        TreeOperation.show(node1);

        TreeNode ret = (new BSTToDList426()).bstToDlist(node1);

        System.out.println(ret.val);
        System.out.println(ret.right.val);
        System.out.println(ret.right.right.val);
        System.out.println(ret.right.right.right.val);
        System.out.println(ret.right.right.right.right.val);
        System.out.println(ret.right.right.right.right.right);

        TreeNode tail = ret.right.right.right.right;
        System.out.println(tail.val);
        System.out.println(tail.left.val);
        System.out.println(tail.left.left.val);
        System.out.println(tail.left.left.left.val);
        System.out.println(tail.left.left.left.left.val);
        System.out.println(tail.left.left.left.left.left);
    }
}
