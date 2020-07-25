package dp;

import java.util.Arrays;
import java.util.Comparator;

/*
给定一组活动时间表，求可以参加的最多的活动数
贪心策略：选择最早结束的活动，删除与其有交集的位置；继续

[[1,3],[2,4],[3,6]] 返回 2

 */
public class ScheduleTask {

    public int scheludTask(int[][] intervals) {

        if (intervals.length < 2) return intervals.length;

        // 按照 第二个数字升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        int cnt = 1;// 最少一个区间
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] >= end) { //
                // 更新 end
                cnt ++;
                end = cur[1];
            }
        }

        return cnt;
    }
}
