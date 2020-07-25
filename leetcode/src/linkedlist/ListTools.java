package linkedlist;

import basics.ListNode;

public class ListTools {

    // 找中间点 + 不借助 dummyHead 修改链表顺序
    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        // 这样的方法找到的是中间偏左的位置（偶数个而言）
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode cur = slow.next;

        // 不借助 dummyHead 的逆序链表 需要三个指针完成
//        ListNode head2 = reverseList(cur);
        // 即当前节点只需要把自己的后继指向自己的前一个节点，剩下的就是维护指针，原题向后移动一个位置
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
}
