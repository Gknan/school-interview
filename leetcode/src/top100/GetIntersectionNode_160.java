package top100;

import basics.ListNode;

/*
编写一个程序，找到两个单链表相交的起始节点。

注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

分析
1，两个链表都是没有循环的单链表
2，若两个链表相交，则从相交位置开始，后面都相交
3，两个指针，快指针先移动到 (long - shrot) 位置，然后快慢指针同时移动，移动过程中判断是否是同一个，返回第一个满足

另外思路
若相交， a + c(c是公共部分 A链表长度) b + c(B 链表长度、)
a + c + b + c = b + c + a + c
走过的路长度相等，速度一样，最后一段路一起走


 */
public class GetIntersectionNode_160 {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode fast = headA;
        ListNode slow = headB;

        int cntA = countList(headA);
        int cntB = countList(headB);

        System.out.println("CntA: CntB: " + cntA + " " + cntB);

        fast = (cntA - cntB) >= 0 ? headA : headB;
        slow = (cntA - cntB) >= 0 ? headB : headA;

        int cnt = 0;
        while (cnt < Math.abs(cntA - cntB)) {
            fast = fast.next;

            cnt ++;
        }

        while (fast != null && slow != null) {
            if (fast == slow) {
                return fast;
            }

            // update
            fast = fast.next;
            slow = slow.next;
        }

        return null;
    }

    private static int countList(ListNode head) {
        int cnt = 0;
        while (head != null) {
            cnt ++;

            head = head.next;
        }
        return cnt;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node6;

        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        System.out.println(getIntersectionNode(node1, node4).val);
    }
}
