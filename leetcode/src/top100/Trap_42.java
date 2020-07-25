package top100;

import java.util.Stack;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。


上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水
（蓝色部分表示雨水）。

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6

分析：
1.按每个位置求，求每个位置在接到雨水后的雨水量，该位置的雨水量等于 该位置 左右两边的最高位置的较小值 - 当前的位置柱子高度
maxLeft 记为左边往右的最大值，maxRight 记录右边往左的最大值 maxLeft maxRigit 是数组；从左往右走，更新 maxLeft 的值
, 那么，maxLeft[i] 就是0~i-1 之间的最高值，maxRight 记录的是 i+1 ~n-1 的最大值，这相当于两个边界；最后，遍历一遍记录
结果值。

2,使用栈，遍历过程中，如果当前条形块长度小于等于栈顶，说明当前条形块能存的雨水的左边界是栈顶，然后将当前条形快加入到栈中；
若当前条形快长度大于栈顶，说明栈顶的存水量是由栈顶的前一个元素作为左边界，当前作为右边界来决定的，弹出栈顶，并计算栈顶元素负责
的雨水量，计算雨水量使用的公式是 索引差 * (两个边界的小值) 累加结果，当前元素入栈，移动指针

3，双指针法，leftMax 记录从左边开始的已得到的最大值，rightMax 记录从右边开始已经得到的最大值
left  right 指针网中间移动；若 leftMax < rightMax left 位置的雨水高度就确定了

 */
public class Trap_42 {

    public int trap3(int[] height) {
        if (height == null || height.length < 3) return 0;

        // [2 0 2] 失败
//        int leftMax = height[0], rightMax = height[height.length - 1];
//        int left = 1, right = height.length - 2;
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        int left = 0, right = height.length - 1;
        int ret = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                ret += leftMax - height[left];
                left ++;
            } else {
                ret += rightMax - height[right];
                right --;
            }
        }

        return ret;
    }

    public int trap2(int[] height) {

        if (height == null || height.length < 3) return 0;

        // 栈中放的是 下标 不是元素大小
        Stack<Integer> stack = new Stack<>();
//        stack.push(0);

        int cur = 0;
        int ans = 0;
        while (cur < height.length) {
//            int top = stack.pop();
//            int top = stack.peek();
//
//            if (height[top] < height[cur]) {
            // 这里是 while 就保证了加入的元素一定是 栈顶大于等于下面的元素，若不是，则进行计算统计
            while (!stack.isEmpty() && height[stack.peek()] < height[cur]) {
                int top = stack.pop();
                if (!stack.isEmpty()) { // 此时栈不空再进行这些计算 如果是单调增的序列这里就是一个栈顶而已
                    int width = cur - stack.peek() - 1;
                    int curHeight = Math.min(height[stack.peek()], height[cur]) - height[top];
                    ans += width * curHeight;
                }
            }

            stack.push(cur);

            cur++;
        }

        // 对于有左右边界的才能接到雨水，所以栈可能不为空

        return ans;
    }

    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        int maxL = Integer.MIN_VALUE, maxR = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            maxL = Math.max(maxL, height[i]);
            maxLeft[i] = maxL;
        }

        for (int i = height.length - 1; i >= 0; i--) {
            maxR = Math.max(maxR, height[i]);
            maxRight[i] = maxR;
        }

        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            ret += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return ret;
    }
}
