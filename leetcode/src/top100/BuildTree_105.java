package top100;

import basics.TreeNode;
import basics.TreeOperation;

import java.util.HashMap;

/*
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7

分析：
前序确定了根节点，中序找到根节点，数组分成两部分，左子树是根节点的左边的节点构成，右子树是根节点的右边的节点构成
再计算根据左子树的前序和中序构造左子树；根据右子树前序和中序构造右子树

优化：由于值不会重复，每次从inorder 中差值麻烦，使用 hashmap<> 保存 value - index 的映射，查找就方便了

 */
public class BuildTree_105 {

    private static HashMap<Integer, Integer> map;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) return null;

        // update map
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }


        return build(0, preorder.length - 1, 0, inorder.length - 1,  preorder, inorder);
    }

    private static TreeNode build(int preLeft, int preRight, int inLeft, int inRight, int[] preorder, int[] inorder) {
//        if (preLeft >= preRight) return new TreeNode(preorder[preLeft]);
        if (preLeft > preRight) return null;

        // root
        TreeNode root = new TreeNode(preorder[preLeft]);

        // find preLeft.val in inorder
//        int inMid = findMidInorder(preorder[preLeft], inorder, inLeft, inRight);
        int inMid = findMidInorder2(preorder[preLeft]);

        // leftTree length
//        int leftTreeLen = inMid - inLeft + 1;
        int leftTreeLen = inMid - inLeft;

        // 划分成两部分继续求
        root.left = build(preLeft + 1, preLeft + leftTreeLen, inLeft, inMid - 1, preorder, inorder);
        root.right = build(preLeft + leftTreeLen + 1, preRight, inMid + 1, inRight, preorder, inorder);

        return root;
    }

    // find index in inorder in map
    private static int findMidInorder2(int target) {
        return map.get(target);
    }

    private static int findMidInorder(int target, int[] nums, int left, int right) {

        boolean flag = false;
        int idx = left;
        while (idx <= right) {
            if (nums[idx] == target) {
                flag = true;
                break;
            }
            idx ++;
        }

        if (flag) {
            return idx;
        } else {
            throw new IllegalArgumentException("Cant find target in nums.");
        }
    }

    public static void main(String[] args) {
//        int[] preorder = {3,9,20,15,7};
//        int[] inorder = {9,3,15,20,7};

        int[] preorder = {3,9};
        int[] inorder = {9,3};


        TreeNode root = buildTree(preorder, inorder);
//        TreeNode node1 = new TreeNode(10);
//        TreeNode node2 = new TreeNode(5);
//        TreeNode node3 = new TreeNode(-3);
//        TreeNode node4 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(2);
//        TreeNode node6 = new TreeNode(11);
//        TreeNode node7 = new TreeNode(3);
//        TreeNode node8 = new TreeNode(-2);
//        TreeNode node9 = new TreeNode(1);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.right = node6;
//        node4.left = node7;
//        node4.right = node8;
//        node5.right = node9;

        TreeOperation.show(root);
    }
}
