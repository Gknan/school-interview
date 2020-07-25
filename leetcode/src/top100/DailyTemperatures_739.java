package top100;

import java.util.ArrayDeque;
import java.util.Arrays;

/*
根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

分析：
1，暴力法，每天向后找，找到结果后填入新创建的表中 时间 O(N^2)
2，单调栈，从后向前遍历，栈中保留递减的数的下标，当前数字更新栈后，前一个元素的就是他后面的第一个大于它的值的下标 时间 O(N)
因为每个元素之多进出，2次操作

 */
public class DailyTemperatures_739 {
    public static int[] dailyTemperatures(int[] T) {
        if (T.length <= 1) return new int[T.length];

        int n = T.length;
        // 单调栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] ret = new int[n];
//        stack.add(T[n - 1]);
        for (int i = n - 1; i >= 0 ; i--) {
//            while (!stack.isEmpty() && stack.peek() <= T[i]) {
            // 注意：栈中保存的是下标
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.poll();
            }

            ret[i] = stack.isEmpty() ? 0 : stack.peek() - i;

            // 入栈
            stack.push(i);
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println(Arrays.toString(dailyTemperatures(T)));
    }
}
