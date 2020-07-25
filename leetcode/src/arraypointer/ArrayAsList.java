package arraypointer;

/**
 * 把数组当做链表来找重复值
 * 这种问题数组中的值 的最大值 小于等于 数组的长度 N
 * 数组中的值可以看做是指向下一个位置的指针


 5，映射为链表的解法
 1 2 3 4 5 6 7 8 9 5
 从 0 下标开始，数组的值相当于下一个位置的指针，得到的链表是 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 5, 6, 7, 8 ,9 出现了环
 若没有重复 如 1 2 3 4 得到的链表 0 ，1， 2， 3， null 最终越界
 而重复的数字就是环的入口节点
 所以，问题转换为求环的入口节点；分成两部，第一，快慢指针找到相交点；第二，设置第三个指针，和满指针一起找到入口
 假设 slow fast 相交是，slow 走了n，fast 走了 2n；
 环入口到开始的位置为 k； 2n - n 是fast 比 slow 多走的位置，实际上就是环内的位置有 n%c = 0; c 是 环的周长
 那么，slow 在环内走的距离是 n - m，若 slow 再向前走 m 步，则在环内的距离就是 n；n % c = 0; 所以就到了环的入口

 */
public class ArrayAsList {

    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;

        int slow = 0;
        int fast = 0;
        // 快慢指针找到环中的相交点
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        // 快指针回退，找环入口位置
        int pointer = 0;
        while (nums[pointer] != nums[slow]) {
            slow = nums[slow];
            pointer = nums[pointer];
        }

        return slow;
    }
}
