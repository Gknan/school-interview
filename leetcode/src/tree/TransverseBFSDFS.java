package tree;

import basics.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TransverseBFSDFS {

    // 翻转二叉树

    // 递归写法
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


    // BFS 写法 借助队列
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



    // DFS 写法 借助栈
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
}
