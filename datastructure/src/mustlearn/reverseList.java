package mustlearn;

import java.util.List;

/**
 * 翻转链表相关题目
 */
public class reverseList {

    static ListNode next = null;
    // 翻转前 n 个
    public static ListNode reverseN(ListNode node, int n) {
        if (n == 1) {
           next = node.next;
           return node;
        }

        ListNode last = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = next;

        return last;

    }

    // 递归实现部分链表翻转 从 m 到 n 的链表翻转
    public static ListNode reversePart(ListNode node, int m, int n) {

//        return help(node, m, n, 1);
        return null;
    }

    // 以  node 为第一个位置，翻转其第 m 到 第 n 个位置后，返回根
    // 数组第一个位置是新的头，第二个位置时中间部分的翻转后的为节点
    private static ListNode[] help(ListNode node, int m, int n, int i) {
        if (m < 0 && n < 0) return new ListNode[] {node, null};// 不用翻转，直接返回
        if (m == 0) {
            ListNode[] nodes = help(node.next, 0, n - i, i + 1);
            node.next = nodes[1].next;
            nodes[1].next = node;
            return new ListNode[] {nodes[0], node};
        }
//        if (m == 0) return help(node, m, n, i);
        if (n == 0) return new ListNode[]{node, node};

        return help(node.next, m - i, n - i, i + 1);
    }

    // 递归实现翻转单向链表
    public static ListNode reverseAll(ListNode node) {

//        if (node == null) return null;
//        if (node.next == null) return node;
        if (node == null || node.next == null) return node;

        ListNode newHead = reverseAll(node.next);

        node.next.next = node;
        node.next = null;


        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        printList(node1);

        printList(reverseAll(node1));

    }

    private static void printList(ListNode node1) {

        StringBuilder sb = new StringBuilder();

        while (node1 != null) {
            sb.append(node1.val + "->");

            // update node
            node1 = node1.next;
        }

        System.out.println(sb.toString());


    }
}
