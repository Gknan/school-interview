package top100;

import basics.ListNode;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

分析：
两个树相加是从低位，逐位相加的，所以使用两个指针，同步移动，相加的结果创建一个节点保存；
考虑到进位，锁还需要一个数字表示进位；
新链表的生成是尾插法

注意 ，5 + 5 可能导致产生新的位置，所以最后要检查进位

另外的做法，遍历两个链表，计算出其对应的值，保存后相加，相加结果再构建链表；但是这里会存在一个问题
链表出来的数字，可能会超过 int  long 等的表示范围，所以不可取；Python 可以处理这样的问题

 */
public class AddTwoNumbers_2 {

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        int carry = 0; // 进位

        while (l1 != null || l2 != null) {
            // 结束条件是连个指针都指向空，链表遍历完毕
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;

//            sum = sum % 10;
            carry = sum / 10;// carry = sum / 10 求进位的公式

            tail.next = new ListNode(sum % 10);
            tail = tail.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry != 0) tail.next = new ListNode(carry);

        return dummyHead.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        int add_1 = 0; // 进位

        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + add_1;

            if (val >= 10) {
                val = val % 10;
                add_1 = 1;
            } else {
                add_1 = 0;
            }

            tail.next = new ListNode(val);
            tail = tail.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            // 进位的处理
            int val = l1.val + add_1;
            if (val >= 10) {
                val = val % 10;
                add_1 = 1;
            } else {
                add_1 = 0;
            }

            tail.next = new ListNode(val);
            tail = tail.next;

            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val + add_1;
            if (val >= 10) {
                val = val % 10;
                add_1 = 1;
            } else {
                add_1 = 0;
            }

            tail.next = new ListNode(val);
            tail = tail.next;

            l2 = l2.next;
        }

        // 最后在对 进位判断
        if (add_1 == 1) tail.next = new ListNode(1);

        tail = dummyHead.next;
        dummyHead.next = null;

        return tail;

    }
}
