package top100;

import basics.ListNode;

/*
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5

分析：
1，链表值拿出来存到数组，数组排序后刷新到链表；空间复杂度 O(N)
2，nlogn 的排序算法有 快排 归并 堆排序
尝试用归并排序解决
递归版本牵扯到调用程序栈的空间为 O(logN)
 */

public class SortList_148 {

    // 非递归版本
    public static ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode tail = null;

        int length = countLength(head);
        for (int i = 1; i < length; i = (i << 1)) {
            ListNode cur = dummyHead.next;
            // update tail 每一次步长的更新都是从头到尾的
            tail = dummyHead;

            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, i);
                // updata cur
                cur = split(right, i);
                tail.next = merge(left, right);

                while (tail.next != null)
                    tail = tail.next;
            }
        }

        return dummyHead.next;
    }

    private static int countLength(ListNode root) {
        int cnt = 0;
        while (root != null) {
            cnt ++;
            root = root.next;
        }

        return cnt;
    }

    // 按照步长分割链表，并返回分割后的后半段的头节点
    private static ListNode split(ListNode node, int step) {

        ListNode cur = node;
        ListNode pre = null;

        int i = 0;
        // 怎么打断
        for (; i < step && cur != null; i ++) {
            pre = cur;
            cur = cur.next;
        }

        if (i == step) {
            // 打断
            pre.next = null;
        }

        return cur;
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        if (left != null) tail.next = left;
        if (right != null) tail.next = right;

        return dummyHead.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // fast 重用
        fast = slow.next;
        // 前半段和后半段打断
        slow.next = null;

        ListNode pre = sortList(head);
        // slow 执行后半段中间位置
        ListNode post = sortList(fast);

        // 合并前后两段
//        head = pre.val <= post.val ? pre : post;

        ListNode dummyHead = new ListNode(-9999909);
        ListNode tail = dummyHead;

        ListNode cur = null;
        while (pre != null && post != null) {
            if (pre.val <= post.val) {
                cur = pre;
                pre = pre.next;
                cur.next = null;
                // 尾插到 dummyHead 链表
                tail.next = cur;
                tail = tail.next;
            } else {
                cur = post;
                post = post.next;
                cur.next = null;
                tail.next = cur;
                tail = tail.next;
            }
        }

        if (pre != null) {
            tail.next = pre;
        }

        if (post != null) {
            tail.next = post;
        }

        head = dummyHead.next;
        dummyHead.next = null;

        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode ret = sortList2(node1);
        System.out.println(ret.val);

    }
}
