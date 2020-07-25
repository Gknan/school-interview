package tree;

import basics.TreeNode;
import top100.InorderTraversal_94;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
借助 栈实现非递归遍历

While Gray 标记法：
前中后序遍历使用同样的框架代码
核心思想：
1、使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色
2，如果遇到的节点为白色，将其标记为灰色，然后将其右子节点，自身、左孩子以此入栈
3，如果遇到的节点为灰色，则访问该节点的值

 */
public class NonRecuretionTrans {


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
        stack.push(new ColorNode(false, root)); // 根节点入栈

        while (!stack.isEmpty()) {
            ColorNode cur = stack.pop();
            if (cur.node == null) continue; // 弹出栈进行一次判断
            if (cur.color == false) {// 新节点，还没有访问过，按照一定顺序把自己 左 右孩子加入到栈中；在压入根之前，把根置为已访问
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
}
