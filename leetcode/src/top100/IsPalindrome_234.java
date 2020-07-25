package top100;

import basics.ListNode;


/*
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

分析：
1，借助栈，队列中前一半元素入栈；然后每遍历一个元素，出栈一个元素比较；时间 O(N) 空间 O(N)
2，从链表结构角度尝试分析，如果是数组，可以使用双指针的方法
所以先知道链表的中间位置，断开链表，把后半部分链表指向改成相反方向；然后比较两个链表
时间 O(N) 空间 O(1)

 */
public class IsPalindrome_234 {

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode cur = slow.next;

        // 不借助 dummyHead 的逆序链表 需要三个指针完成
//        ListNode head2 = reverseList(cur);
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            // 保存next
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        ListNode p = head, q = pre;

        boolean flag = true;
        while (q != null && p != q) {

//System.out.println("pval: " + p.val + ", qval: " + q.val);
            if (p.val != q.val){
                flag = false;
                break;
            }

            // update
            p = p.next;
            q = q.next;

        }

        // 恢复链表
//        baxkList(head2);
//        reverseList(head2);

        return flag;
    }

    // 计算链表中节点个数
//    private static int countList(ListNode head) {
//        int cnt = 0;
//        while (head != null) {
//            head = head.next;
//
//            cnt ++;
//        }
//
//        return cnt;
//    }

    private static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode dummyHead = new ListNode(-1);
        ListNode post = null;

        while (cur != null) {
            post = cur.next;

            // 头插法逆序
            cur.next = dummyHead.next;
            dummyHead.next = cur;

            // update
            cur = post;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

//        System.out.println(countList(node1));

        System.out.println(isPalindrome(node1));

    }
}
