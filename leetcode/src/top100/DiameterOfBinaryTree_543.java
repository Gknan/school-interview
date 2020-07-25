package top100;

import basics.TreeNode;

/*
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
这条路径可能穿过根结点。

示例 :
给定二叉树

          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示

分析：
一颗二叉树的路径长度一定是 左子树的路径长度 + 右子树的路径长度 + 2（若左右不为空）
其实相当于求左子树和右子树的深度

出问题的点：最长路径可能不经过根节点；
对于每个节点，都可能是最长路径的根节点；所以求完所以节点的 最长路径，过程中更新 max

 */
public class DiameterOfBinaryTree_543 {

    private int max = 0;

    public int diameterOfBinaryTree2(TreeNode root) {
        dfs(root);

        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftDis = dfs(root.left);
        int rightDis = dfs(root.right);

        // update max
        max = Math.max(max, leftDis + rightDis);
//        return leftDis + rightDis + 1;
        return Math.max(leftDis, rightDis) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        return countDis(root.left) + countDis(root.right);
    }

    private int countDis(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(countDis(root.left), countDis(root.right)) + 1;
    }
}
