package top100;

import basics.TreeNode;

/*
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3，
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
说明:

如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

分析：
1，递归方法：

2，中序遍历得到结果串；然后从中间开始，每次同时向左右移动一格比较是否相等，若有一个不等，
结束返回 false；或者借助一个栈
失败 case： [1,2,2,2,null,2]

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class IsSymmetric_101 {

    public static boolean isSymmetric3(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();

//            if (t1 == null && t2 == null) return true;
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;

            if (t1.val != t2.val) return false;

            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }

        return true;
    }


    // 一个数镜像，则 这个数的左子树和右子树互为镜像
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return t1.val == t2.val
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        ArrayList<Integer> list = new ArrayList<>();
        inOrderTrans(root, list);

        System.out.println(list);

        int i = list.size() / 2;
        int k = 1;
        while ((i + k) < list.size()) {
            if (list.get(i + k) != list.get(i - k)) {
                return false;
            }

            // update k
            k ++;
        }

        return true;
    }

    private static void inOrderTrans(TreeNode root, ArrayList<Integer> list) {
        if(root == null) {
            return;
        }

        inOrderTrans(root.left, list);
        list.add(root.val);
        inOrderTrans(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
//        TreeNode node1 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;

        System.out.println(isSymmetric(node1));
    }
}
