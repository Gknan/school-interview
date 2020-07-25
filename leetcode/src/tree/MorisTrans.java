package tree;

import basics.TreeNode;

/*
moris遍历
若当前节点的左子树不空，找到前序 pre，pre.next = cur; cur = cur.left;
若当前节点的左子树为空，处理 cur，cur= cur.right
若当前节点的前序指向自己，pre.next = null; cur = cur.right;
 */
public class MorisTrans {

    // Moris 遍历
    public static void morisTrans(TreeNode root) {

        while (root != null) {
            if (root.left != null) { // 左边不为空，进入找到左边的中序最右节点
                TreeNode pre = getPrecessor(root);
                if (pre.right == null) { // 第一次到达中序最右 右指针指向 root
                    pre.right = root;
                    root = root.left; // 更新 root 到 root.left
                } else {
                    pre.right = null; // 若第二次到达 前驱节点，还原 前驱的指向为 null
                    System.out.println(root.val); // TODO 操作逻辑
                    root = root.right; // 此时说明 root 的左边已经完成，进入右边
                }
            } else { // 若没有左子树，访问根，进入右子树
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    // 找当前节点 node 的前驱节点
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

}
