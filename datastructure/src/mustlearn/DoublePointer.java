package mustlearn;

/**
 * 快慢指针方法解决链表中环的判断问题
 */
public class DoublePointer {

    boolean hasCycle(ListNode head) {
        ListNode fast, slow;

        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;
        }

        return false;
    }

    // 寻找链表的中间
    public ListNode findListCenter(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 链表个数为奇数，返回中间；偶数，返回中间偏右
        // 该方法能用于链表的归并排序
        return slow;
    }

    /**
     * 查看链表的倒数第k个节点
     * fast 先走k 步，然后slow 从头，同步走
     * @param head
     * @return
     */
    public ListNode findKNode(ListNode head, int k) {
        ListNode fast = head, slow = head;

        // fast 走了 k 步
        while (k -- > 0) {
            fast = fast.next;
        }

        while ( fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }
}
