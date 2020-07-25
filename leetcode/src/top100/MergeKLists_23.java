package top100;

import basics.ListNode;

import java.util.PriorityQueue;

/*
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6

分析：
多路排序，
1，取出节点中的值，对值进行排序后输入到节点，重建
2，方法二，两个一排序，总共排序 N-1 次
3，先将每个链表内部排序，然后总排序一次，每个链表已经是有序状态
那么，使用 HashMap 记录每个链表的头，借助 dummyHead 尾插法
其实 用 PriorityQueue 更合适，因为我们是比较 K 个中的最小值
小根堆

 */
public class MergeKLists_23 {

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;
        if (lists.length < 2) return lists[0];

        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(
                (o1, o2) -> o1.val - o2.val
        );

        for (int i = 0; i < lists.length; i++) {
            // 边界，节点可能为空，这里传入的是空，数组个数不为1，但是多个位置是空的情况
            if (lists[i] != null)
                minHeap.add(lists[i]);
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;

        while(!minHeap.isEmpty()) {
            ListNode cur = minHeap.poll();

            // 先把后继加进来，断开连接
           if (cur.next != null) minHeap.add(cur.next);
           // 断开连接
           cur.next = null;
           tail.next = cur;
           tail = tail.next;
        }

        return dummyHead.next;

    }
}
