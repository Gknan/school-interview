package RegionProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
合并有交集的区间
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
 */
public class MergeLapedRegion {


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
        int start = intervals[0][0];// 合并区间的起始值
        int end = intervals[0][1]; // 合并区间的终点值
        for (; i < intervals.length; i ++) {
            int[] cur = intervals[i];
            if (cur[0] > end) {
                // 收集前一个合并区间
                retList.add(new int[] {start, end});

                // 重置 start end
                start = cur[0];
                end = cur[1];
            } else {
                // 当前的合并区间 更新合并区间的两个端点
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
    }
}
