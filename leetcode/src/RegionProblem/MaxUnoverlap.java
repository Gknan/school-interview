package RegionProblem;

import java.util.Arrays;
import java.util.Comparator;

/*
求最大的不重叠区间个数

贪心法

 */
public class MaxUnoverlap {

    public int findMaxOverLaps(int[][] points) {
        if (points.length < 2) return points.length;

        // 按照区间的右边界排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 统计有边界的个数
        int cnt = 1;
        int end = points[0][1];
        for(int[] point: points) {
            int start = point[0];
            if (start > end) {
                cnt ++;
                end = point[1];
            }
        }

        return cnt;
    }

}


