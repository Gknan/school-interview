package top100;

import basics.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

借助栈遍历
1，先走到最左边
2，用栈就不用
3，当回溯后，访问根，有右子树，进入右子树，没有右子树，继续回溯
4，若是右孩子回到根，不要再次访问
moris 遍历

While Gray 标记法：
前中后序遍历使用同样的框架代码
核心思想：
1、使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色
2，如果遇到的节点为白色，将其标记为灰色，然后将其右子节点，自身、左孩子以此入栈
3，如果遇到的节点为灰色，则访问该节点的值

 */
public class InorderTraversal_94 {

    public static class ColorNode {
        // color = 0 标识白色；color = 1 表示灰色
        public boolean color;
        public TreeNode node;

        public ColorNode(boolean color, TreeNode node) {
            this.color = color;
            this.node = node;
        }
    }

    // 颜色标记迭代法
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(false, root));

        while (!stack.isEmpty()) {
            ColorNode cur = stack.pop();
            if (cur.node == null) continue;
            if (cur.color == false) {
                // 按顺序加入当前节点的孩子
//                stack.push(new ColorNode(false, cur.node.right));
//                cur.color = true;
//                stack.push(cur);
//                stack.push(new ColorNode(false, cur.node.left));

                // 前序
//                stack.push(new ColorNode(false, cur.node.right));
//                stack.push(new ColorNode(false, cur.node.left));
//                cur.color = true;
//                stack.push(cur);

                // 后序
                cur.color = true;
                stack.push(cur);
                stack.push(new ColorNode(false, cur.node.right));
                stack.push(new ColorNode(false, cur.node.left));

            } else {
                ret.add(cur.node.val);
            }
        }

        return ret;
    }

    // 借助栈实现
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }

        while (!stack.isEmpty()) {
            p = stack.pop();

//            ret.add(p.val);
            // 若当前节点时根的右孩子，说明已经访问过
            if (!visited.contains(p)) {
                ret.add(p.val);
                visited.add(p);
            }

            // p 右孩子存在且访问过
            if (p.right != null && !visited.contains(p.right)) {
                // 若有右孩子，且右孩子没有访问过，压入右孩子之前，先把根压进入
                stack.push(p);
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }

        return ret;
    }


    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        // moris 遍历
        //
        while (root != null) {
            if (root.left != null) {
                // 找到前序节点
                TreeNode pre = precessor(root);
                if (pre.right != root) {
                    pre.right = root;
                    root = root.left;
                } else {
                    // TODO
//                    System.out.println(root.val);
                    ret.add(root.val);
                    pre.right = null;
                    root = root.right;
                }
            } else {
                // TODO
//                System.out.println(root.val);
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }

    public static void moris(TreeNode root) {

//        if (root != null) {
        while (root != null) {
            if (root.left != null) {
                // 找到前序节点
                TreeNode pre = precessor(root);
                if (pre.right != root) {
                    pre.right = root;
                    root = root.left;
                } else {
                    // TODO
                    System.out.println(root.val);
                    pre.right = null;
                    root = root.right;
                }
            } else {
                // TODO
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    private static TreeNode precessor(TreeNode node) {
        TreeNode pre = null;
        if (node.left != null) {
            pre = node.left;
            while (pre.right != null && pre.right != node) {
                pre = pre.right;
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node6;
        node5.right = node7;

//        moris(node1);

        System.out.println(inorderTraversal2(node1));
        System.out.println("=============");
        System.out.println(inorderTraversal3(node1));

    }
}
