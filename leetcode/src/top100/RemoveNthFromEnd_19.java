package top100;

import basics.ListNode;

/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

分析：
删除一个节点，需要找到这个节点的前一个节点 pre，pre.next = pre.next.next; 完成删除
如果是倒数第 n 个节点，就是删除第一个几点，需要单独处理边界，这里添加一个 dummyHead 解决这个问题
使得统一处理
一遍扫描怎么实现 三个指针 pre cur last 分别表示 待删除的节点的前驱；待删除的节点；距离 cur 的第 k（倒数第k中的k） 个节点


 */
public class RemoveNthFromEnd_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        ListNode cur = head;
        ListNode kth = cur;

        while (n -- > 0) kth = kth.next;

        while (kth != null) {
            pre = cur;
            cur = cur.next;
            kth = kth.next;
        }

        // remove
        pre.next = cur.next;
        cur.next = null;

        head = dummyHead.next;
        dummyHead.next = null;// help GC
        return head;
    }
}
