package top100;

/*

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
示例：
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

分析；
新链表是拼接得到，所以不能有额外的空间来存储新的链表
方法1：
p 指向最终链表的头， q 指向需要别合并链表的头；显然，p 的头是连个链表的最小元素
遍历两个链表，若 p.val < q.val 向下找到第一个比 q 大的节点，q 节点摘下插入到这个节点这前 q 向下移动一个位置
继续在 p 链表中找第一个大于该节点的位置，插入；
直到其中一个为空，p 先为空，剩下的 q 查到p 链表后面；若 q 链表为空，返回
思想就是插入排序的思想 比较

方法2：
外排序的思路，两个链表谁小摘下谁，为了避免处理边界，借助一个 dummyHead 会好处理一些

 */
import basics.ListNode;

public class MergeTwoLists_21 {

    // recursion version
    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }

    // without dummyHead
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode head = l1;

        ListNode t;
        // l1 indict min
        while (l2 != null) {
            while (l1 != null && l1.next != null && l1.next.val <= l2.val) {
                l1 = l1.next;
            }

            if (l1 == null) {
                break;
            }
            if (l1.next == null) {
                l1.next = l2;
                break;
            }

            t = l2.next;
            l2.next = l1.next;
            l1.next = l2;
            l2 = t;
        }

        return head;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode r = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                r.next = l1;
                l1 = l1.next;
            } else {
                r.next = l2;
                l2 = l2.next;
            }

            // update r
            r = r.next;
        }

//        while (l1 != null) {
//            r.next = l1;
//        }
//        while (l2 != null) {
//            r.next = l2;
//        }

        // not while , it is if
        if (l1 != null) {
            r.next = l1;
        }
        if (l2 != null) {
            r.next = l2;
        }

        r = dummyHead.next;
        // release
        dummyHead.next = null;

        return r;

    }

    public static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val + " ");

            head= head.next;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        System.out.println(printList(node1));
        System.out.println(printList(node4));
//        System.out.println(printList(mergeTwoLists(node1, node4)));
        System.out.println(printList(mergeTwoLists2(node1, node4)));

    }
}
