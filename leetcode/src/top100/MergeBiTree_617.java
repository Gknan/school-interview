package top100;

import basics.TreeNode;

/*
给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
输出:
合并后的树:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7

注意: 合并必须从两个树的根节点开始。

分析：
层次遍历两棵树，同时遍历，没办法记录每一行的位置。
借助队列实现层次遍历，p 指向 tree1 q 指向 tree2
每次从队列中弹出时，
若 p q 都不是null，更该p 中值为两者和
若 p 为null，q 不为null，新增加一个节点，值为 q 的值， r 指向结果树
否则，继续向下处理。

时间复杂度：O(N) N 是Max(tree1, tree2) 节点数；空间复杂度 O(N)

先写出层次遍历的框架，再在基础上进行修改

方法一：递归
对两棵树同事进行前序把你，并将对应的节点进行合并、
在遍历时，如果两棵树的当前节点均不为空，将他们的值进行相加，并对他们的左孩子
和右孩子进行递归合并；如果其中有一棵树为空，我们返回另一棵树作为结果；如果两颗树都是空，此时返回任意一棵树
均可。

方法二：迭代
先把两棵树的根节点入栈，栈中的每个元素都会存放两个根节点，并且栈顶的元素是需要处理的节点。
在迭代的每一步中，我们取出栈顶的元素并把它移出栈，并将它们的值相加。随后我们分别考虑
两个节点的左孩子和右孩子，如果两个节点都是做孩子，那么久将左孩子入栈；如果只有一个节点有
左孩子，那么僵其作为第一个节点的左孩子；如果都没有左孩子，那么不用做任何事情。

层次遍历迭代法；两个树同时入队，出队后进行判断，更新 t1 上的节点值。
然后判断 p.left，若 p.left == null && q.left != null ;为p 创建一个节点左节点；同理右节点同样处理
若 p.left != null && q.lfet == null ,为 q 创建新的节点

 */

import java.util.LinkedList;
import java.util.Stack;

public class MergeBiTree_617 {

    // preOrder 递归
    public static TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        t1.val += t2.val;

        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }

    // 迭代
    public static TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {

        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});

        while (!stack.isEmpty()) {
            TreeNode[] top = stack.pop();
            if (top[0] == null || top[1] == null) {
                continue;
            }

            top[0].val += top[1].val;
            if (top[0].left == null) {
                top[0].left = top[1].left;
            } else {
                stack.push(new TreeNode[] {top[0].left, top[1].left});
            }

            if (top[0].right == null) {
                top[0].right = top[1].right;
            } else {
                stack.push(new TreeNode[] {top[0].right, top[1].right});
            }

        }

        return t1;
    }

    // 只需要给 t1 中增加 t2 中 t1 没有的节点，所以以 t2 的完整遍历为主
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        // t1 != null && t2 != null

        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        TreeNode p = null;
        TreeNode q = null;

        queue1.addLast(t1);
        queue2.addLast(t2);

        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            p = queue1.poll();
            q = queue2.poll();

            // process TODO
            p.val += q.val;

            if (q.left != null) {
                if (p.left == null) {
                    p.left = new TreeNode(0);
                }
                queue1.addLast(p.left);
                queue2.addLast(q.left);
            }


            if (q.right != null) {
                if (p.right == null) {
                    p.right = new TreeNode(0);
                }
                queue1.addLast(p.right);
                queue2.addLast(q.right);
            }
        }


        return t1;
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
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//
//        tranverse(node1);

        // testCase
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;

        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(4);
        TreeNode node9 = new TreeNode(7);
        node5.left = node6;
        node5.right = node7;
        node6.right = node8;
        node7.right = node9;

        System.out.println("Tree1:");
        tranverse(node1);
        System.out.println("=========================");
        System.out.println("Tree2:");
        tranverse(node5);

        System.out.println("=========================");
        System.out.println("Result:");
        tranverse(mergeTrees(node1, node5));



    }
}
