package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

not ac : [[1,4],[0,4]] 预期 [[0,4]] 这样的就不好处理 所以先把原来的数组按照第一个元素进行排序，然后再比较

经过排序后，第一个元素是递增的关系，知识后只需要考虑第二个元素


所以，这里的比较，若是 preLast == nexFront 拼接
preLast < nexFront 肯定是断开的 假设两个数组是有序的
preLast > nexFront，若 preFront > nextFront 取出头，继续比较，直接加；若 preFront <= nextFront 直接加


分析：
1.从第一个位置开始比较，比较arr[0][1] 和 arr[1][0] 若大于等于，则表示0 和 1 可以合并，更新 当前的比较位数字为 arr[1][1]
ret 继续和下一个 arr[2][0] 比较，若 ret < arr[2][0] ，截止 ret 的第一个重叠区域找到；下一次从 arr[2][0] 位开始找
2，使用 list 纪录每个重叠区域的元素，每次比较 list 的最后一个元素的第二位和 下一个元素的第一位，重合添加进去，知道不重合，
纪录结果，清空list；继续下一个重叠区域的寻找

三种情况 排序后
1,3  4,5    添加 save，移动 save，移动 cur
1,4  2,3    移动 cur
1,4  3,5    更新 save ，移动 cur


按照求不重叠的区间问题来求
1，先按照 第二位 排序
2，每个区间内更新两个端点，在下个区间开始前一个区间的合并结果进行统计
上面的方法并不能很好滴通过case [[2,3],[4,5],[6,7],[8,9],[1,10]]

将原来的数组按照 第一个元素排序；排完序后，若当前的合并区间的两个端点是 start end
那么，对于新的位置，我们先判断 cur[0] > end,如果成立，说明前面一个合并区间找到；否则，更新 合并区间的 end；由于 start 是排完
序的，所以 start 不用更新

问题是按照第二个位置排序后，为什么不能找到结果

 */
public class Merge_56 {

    // 排序后双指针解决
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
            return new int[0][0];

        if (intervals.length < 2) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        ArrayList<int[]> retList = new ArrayList<>();

        int i = 1;
//        int preEnd = intervals[0][1];
        int start = intervals[0][0];// 合并区间的起始值
        int end = intervals[0][1]; // 合并区间的终点值
        for (; i < intervals.length; i ++) {
            int[] cur = intervals[i];
            if (cur[0] > end) {
//                end = cur[1];
                // 收集前一个合并区间
                retList.add(new int[] {start, end});

                // 重置 start end
                start = cur[0];
                end = cur[1];
            } else { // 这里是 if else ，如果不加 else ，前面的逻辑完了会直接执行下面的
                // 当前的合并区间 更新合并区间的两个端点
//                start = Math.min(start, cur[0]);
                end = Math.max(end, cur[1]);
            }
        }

        // 最后可能剩一组值 需要单独加进入
        retList.add(new int[]{start, end});

        int[][] ret = new int[retList.size()][2];
        for (int j = 0; j < ret.length; j++) {
            ret[j][0] = retList.get(j)[0];
            ret[j][1] = retList.get(j)[1];
        }

        return ret;

        // save 指针指向 前一个保存的数组， cur 指针指向正在遍历的位置
        // ruo save[][1] < cur[][0] save 压入结果，save 向下，cur ++
        // save[][1] = cur[][0] 更新 save[][1] 位置的值为 cur[][0] cur ++
        // save[][1] > cur[][0]  更新  save[][1] cur++
    }

//
//    public static int[][] merge(int[][] intervals) {
//        if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
//            return new int[0][0];
//
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        });
//
//        ArrayList<int[]> ret = new ArrayList<>();
//
//        LinkedList<int[]> list = new LinkedList<>();
//        list.add(new int[]{intervals[0][0], intervals[0][1]});// 添加第一行
//        for (int i = 1; i < intervals.length; i ++) {
//            int nextFront = intervals[i][0];
//            int preLast = list.getLast()[1];
//            int nextLast = intervals[i][0];
//            int preFront = list.getLast()[0];
//            /*if (preLast >= nextFront) {
//                list.add(new int[] {intervals[i][0], intervals[i][1]});
//            } else {
//                // 区间段开，收集结果，清空后添加新的子序列
//                ret.add(new int[] {list.getFirst()[0], list.getLast()[1]});
//                while (!list.isEmpty()) list.poll();
//                list.add(new int[] {intervals[i][0], intervals[i][1]});
//            }*/
//            if (preLast < nextFront) {
//                // 区间段开，收集结果，清空后添加新的子序列
//                ret.add(new int[] {list.getFirst()[0], list.getLast()[1]});
//                while (!list.isEmpty()) list.poll();
//            } else if (preLast == nextFront) {
//                list.add(new int[]{intervals[i][0], intervals[i][1]});
//            } else if (preLast > nextFront && preLast <= nextLast) {
//                list.add(new int[]{intervals[i][0], intervals[i][1]});
//            } else if (preLast > nextFront && preLast > nextLast) {
//            }
//        }
//
//        // 若 list  不为空，处理最后一个
//        if (!list.isEmpty()) {
//            ret.add(new int[] {list.getFirst()[0], list.getLast()[1]});
//        }
//
//        int len = ret.size();
//        int[][] ans = new int[len][2];
//        for (int i = 0; i < len; i++) {
//            // remove 比 直接取值效率高
//            int[] ele = ret.remove(0);
//            ans[i][0] = ele[0];
//            ans[i][1] = ele[1];
//        }
//
//        return ans;
//    }

    private static void print2DMatrix(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

//        int[][] arr = {{1,3},{2,6},{8,10},{15,18}};
        int[][] arr = {{1,3},{3,6}};
        int[][] ret = merge(arr);
        print2DMatrix(ret);
//        System.out.println(Arrays.toString(ret[0]));
//        System.out.println(Arrays.toString(ret[1]));
    }
}
