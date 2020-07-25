package arraypointer;

import java.util.ArrayList;

/*
给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
你找到的子数组应是最短的，请输出它的长度。
示例 1:
输入: [2, 6, 4, 8, 10, 9, 15]
输出: 5
解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 */
public class DoublePointer {


    /*
    同时从前往后和从后往前遍历，分别得到排序数组的右边界和左边界：
    寻找右边界：从前往后遍历的过程中，用 max 记录遍历过的最大值，如果 max 大于当前的
    nums[i]，说明 nums[i] 的位置不正确，应该属于需要排序的数组，以为将右边界更新为 i，
    否则，更新 max，这样最终可以找到需要排序的右边界，右边界之后的元素都是大于 max 的，是有序的

    右边界满足的条件是：右边界之后的元素多是有序的且右边界之后的元素的最小值也大于右边界之前的最大值，所以从左到右遍历过程中，需要保留
    已经遍历到的最大值；
    同理，左边界的条件是：左边界之前的元素是有序的雀小于左边界之后元素的最小值；
     */
    public static int findUnsortedSubarray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int right = 0, left = nums.length - 1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                // nums[i] < max 说明当前的 i 的位置不正确
                right = i;
            }
        }

        for (int j = nums.length - 1; j >= 0; j --) {
            if (nums[j] <= min) {
                min = nums[j];
            } else {
                left = j;
            }
        }

        return Math.max(0, right - left + 1);
    }

    /*
    双指针法的推广，求两个区间列表的交集列表
    输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
    输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     */
    public static int[][] intervalIntersection(int[][] A, int[][] B) {

        if (A.length == 0 || A[0].length == 0
                || B.length == 0 || B[0].length == 0) return new int[0][0];

        ArrayList<int[]> list = new ArrayList<>();

        int i = 0, j = 0; // i -> A  ; j -> B
        while (i < A.length && j < B.length) {
            int[] curA = A[i], curB = B[j];

            if (curA[0] > curB[1]) j ++;
            else if (curA[0] <= curB[1] && curA[1] >= curB[1]) {
                list.add(new int[]{Math.max(curA[0], curB[0]), curB[1]});
                j ++;
            } else if (curB[1] > curA[1] && curB[0] <= curA[1]) {
                list.add(new int[]{Math.max(curA[0], curB[0]), curA[1]});
                i ++;
            } else {
                i ++;
            }
        }

        // for test
//        for (int[] ele: list) {
//            System.out.println(Arrays.toString(ele));
//        }
        int[][] ret = new int[list.size()][2];
        for (int k = 0; k < ret.length; k++) {
            ret[k][0] = list.get(k)[0];
            ret[k][1] = list.get(k)[1];
        }
        return ret;

    }


    // 双指针找两根柱子，值得柱子之间存水量最大
    /*
    第一步 i=0，j=8；此时的面积等于 底 * 高；此时 i 柱子和短柱子，j 柱子是长柱子；
    假设 j 柱子向左移动，那么 相比上一步，底的长度减少1，移动后的水位高度不会超过原来的高度；面积一定减少
    假设 i 柱子向左移动，底减少了，但是高可能增加，最终反映的是面积可能增加或者减少
    所以我们移动短柱子，那么 怎么证明移动不会错过解呢？
    解空间排除法，二维矩阵解空间，对于这个问题，  0<=i<=8 0<=j<=8 有效解空间是  i<j   0<=i<=8  0<=j<=8 其实是上三角
    最开始在 (0，8) 处，设右边高，左边低， j --，则 (0, 7) 处的值一定小于当前获得的值，同理可以排除 (0,6)....第一行
    i ++ 后，到达 (1,8) 位置，设左边高，固定左边，那么可以排除 i = 2， 3， .. 这一列
    以此类推，每一步移动指针都会缩减解空间，最终一定可以遍历完了解空间，过程中记录了值，所以不会错过最大值

     */
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;

        int i = 0, j = height.length - 1;
        int curCap;
        while (i < j) {
//            curCap = (height[j] - height[i]) * Math.min(height[i], height[j]);
            // 面积= 底 * 高 底的计算使用的是下标
            curCap = (j - i) * Math.min(height[i], height[j]); // 当前两根柱子之间的水容量
            max = Math.max(max, curCap);
            if (height[i] <= height[j]) {// 每次移动短柱子，向可能的解靠近；若移动长柱子，容量一定减少，不会靠近解
                i ++;
            } else {
                j --;
            }
        }

        return max;
    }

    // 双指针法求 除自己外的积
//    两个数组 leftMulti[] rightMulti[] 分别表示从最左边累乘到当前位置之前的积 和 从最右边累乘到当前位置之前的积
//    ans[i] = leftMulti[i] * rightMulti[i]
//    第一个遍从到右遍历填 leftMulti 表； 第二遍从右到左遍历填 rightMulti 表
//    空间 O(N) 时间 O(N)
    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] leftMulti = new int[n];
        int[] rightMulti = new int[n];

        int[] ans = new int[n];

        // from left to right
        leftMulti[0] = 1;
        for (int i = 1; i < n; i++) {
            leftMulti[i] = leftMulti[i - 1] * nums[i - 1];
        }

        // from right to left
        rightMulti[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
//            rightMulti[i] = rightMulti[i - 1] * nums[i - 1];
            rightMulti[i] = rightMulti[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = leftMulti[i] * rightMulti[i];
        }

        return ans;
    }

}
