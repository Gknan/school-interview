package bishi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


// 注意 long double 类型 溢出的考虑
//   System.out.format("%.2f\n",3.266);
// 排序 的处理
// 先分析清楚再作答
/*

携程呼叫中心7×24小时帮助旅客解决在途中的各种问题，为了尽可能提升服务质量，
公司希望客服人数可以满足所有旅客的来电，不用排队等待人工客服。现在提供客服中心
所有的通话记录时间，你能算出最少需要多少名客服吗？
输入一个n表示要输入的通话记录个数，接下来输入n行，每行为逗号相隔的两个整数，
两个数字分别代表呼入时间和挂断时间的时间戳。 举例：10,30，表示[10,30)，代
表第10秒呼入，第30秒已经挂断，即第30秒可以接入新的来电； 每一行都是一条通话记录
，通话记录已经按呼入时间由小到大排序；

6
0,30
0,50
10,20
15,30
20,50
20,65

 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int[][] arr = {{0, 30}, {0, 50}, {10, 20}, {15,30}, {20,50}, {20,65}};

        int n = scanner.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {

            String[] input = scanner.next().split(",");
            arr[i][0] = Integer.valueOf(input[0]);
            arr[i][1] = Integer.valueOf(input[1]);
        }

        boolean[] visited = new boolean[arr.length];

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            cnt ++;
            int end = arr[i][1];
            for (int j = i + 1; j < arr.length; j++) {
                if (visited[j]) continue;
                if (arr[j][0] >= end) {
                    // 可以被一个人访问
                    visited[j] = true;
                    end = arr[j][1];
                }
            }
        }

        System.out.println(cnt);

    }


    public static int help(int[][] arr) {

        boolean[] visited = new boolean[arr.length];

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            cnt ++;
            int end = arr[i][1];
            for (int j = i + 1; j < arr.length; j++) {
                if (visited[j]) continue;
                if (arr[j][0] >= end) {
                    // 可以被一个人访问
                    visited[j] = true;
                    end = arr[j][1];
                }
            }
        }

        return cnt;
    }

}
