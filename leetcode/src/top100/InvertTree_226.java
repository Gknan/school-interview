package top100;

import basics.TreeNode;

/*
翻转一棵二叉树。
输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

分析：
1 递归
翻转根的左子树，翻转根的右子树，返回头结点
2 BFS
遍历树中所有结点，遍历到的节点进入队列；出队时，交换当前节点的左右孩子
2 DFS

 */

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class InvertTree_226 {

    public static TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {
            return root;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            if (cur == null) {
                continue;
            }

            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            stack.push(cur.right);
            stack.push(cur.left);
        }

        return root;
    }

    public static TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

        return root;
    }

    public static TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }

        swap(root);

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static void tranverse(TreeNode head) {
        StringBuilder sb = new StringBuilder();
        // inOrderTrans

        inOrderTrans(head, sb);

        System.out.println(sb.toString());
    }

    public static void inOrderTrans(TreeNode head, StringBuilder sb) {

        // finish condition
        if (head == null) {
            sb.append("#_");
            return;
        }

        inOrderTrans(head.left, sb);

        // TODO
        sb.append(head.val + "_");

        inOrderTrans(head.right, sb);
    }

    public static void main(String[] args) {

        // testCase
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;

//        TreeNode node5 = new TreeNode(2);
//        TreeNode node6 = new TreeNode(1);
//        TreeNode node7 = new TreeNode(3);
//        TreeNode node8 = new TreeNode(4);
//        TreeNode node9 = new TreeNode(7);
//        node5.left = node6;
//        node5.right = node7;
//        node6.right = node8;
//        node7.right = node9;

        System.out.println("Tree1:");
        tranverse(node1);
        System.out.println("=========================");
//        System.out.println("Tree2:");
//        tranverse(node5);

        System.out.println("=========================");
        System.out.println("Result:");
        tranverse(invertTree(node1));
    }
}
