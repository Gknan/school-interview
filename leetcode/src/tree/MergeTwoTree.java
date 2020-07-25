package tree;

import basics.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

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
 */
public class MergeTwoTree {

    // preOrder 递归
    /*
    对两棵树同事进行前序把你，并将对应的节点进行合并、
在遍历时，如果两棵树的当前节点均不为空，将他们的值进行相加，并对他们的左孩子
和右孩子进行递归合并；如果其中有一棵树为空，我们返回另一棵树作为结果；如果两颗树都是空，此时返回任意一棵树
均可。
     */
    // 重要的是理解这个递归函数的意思：合并 T1 T2 两颗树并返回合并后的树的根
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
    // 重要的就是怎么找到父节点，这里使用 BFS ，当前节点处理时，就把确实的孩子节点创建出来
//    先把两棵树的根节点入栈，栈中的每个元素都会存放两个根节点，并且栈顶的元素是需要处理的节点。
//    在迭代的每一步中，我们取出栈顶的元素并把它移出栈，并将它们的值相加。随后我们分别考虑
//    两个节点的左孩子和右孩子，如果两个节点都是做孩子，那么久将左孩子入栈；如果只有一个节点有
//    左孩子，那么僵其作为第一个节点的左孩子；如果都没有左孩子，那么不用做任何事情。
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
}
