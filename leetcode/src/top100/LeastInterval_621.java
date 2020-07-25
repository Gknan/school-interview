package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，
并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的最短时间。

 

示例 ：

输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 

提示：

任务的总个数为 [1, 10000]。
n 的取值范围为 [0, 100]。

分析：
求 完成所有任务所需要的最短时间
贪心策略，先安排重复次数最多的任务，按照给定的间隔 n 来安排，接着安排次多的

注意，这里的要要求是两个相同任务之间的间隔必须是 n
所以每轮对 map 排序后，选择 前 n+1 大的元素，放入结果中；如果不够 n + 1，填补空，
选择完之后，继续进行排序

方法二：
大跟堆，每次取出对顶的前 n-1 个元素，注意，这里是一个批次取，取出来后先暂存，更改对应的个数，后添加到堆中


 */
public class LeastInterval_621 {

    public static int leastInterval2(char[] tasks, int n) {

        if (n == 0) return tasks.length;

        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A'] ++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < 26; i++) {
            // 只加进去 > 0 的
            if (map[i] > 0) {
                maxHeap.add(map[i]);
            }
        }

        int ret = 0;
        while (!maxHeap.isEmpty()) {
            int i = 0;
            ArrayList<Integer> temp = new ArrayList<>();
            while (i <= n) {
//                if (!maxHeap.isEmpty() && maxHeap.peek() > 1) {
//                    // 暂存
//                    temp.add((maxHeap.poll() - 1));
//                } else {
//                    maxHeap.poll();
//                }
                if (!maxHeap.isEmpty()) {
                    if (maxHeap.peek() > 1) {
                        temp.add((maxHeap.poll() - 1));
                    } else {
                        maxHeap.poll();
                    }
                }

                // 取出一个添加到结果中，所以结果个数 + 1.
                ret ++;

                if (maxHeap.isEmpty() && temp.isEmpty()) {
                    // 已经结束
                    break;
                }
                i ++;
//                ret ++;
            }
            for(int val: temp) {
                maxHeap.add(val);
            }
        }
        return ret;
    }

    public static int leastInterval(char[] tasks, int n) {

        if (n == 0) return tasks.length;
        
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A'] ++;
        }

//        ArrayList<Integer> ret = new ArrayList<>();
        int ret = 0;
        Arrays.sort(map);

//        boolean flag = false; // 是否结束
        while (true) {
            // 最多的元素现在个数为 0，退出
            if (map[25] == 0) break;
            int cnt = 0;
//            for (int i = 26; i >= 0; i--) {

            while (cnt < n + 1) {
                // 为了避免 A 已经分配完了，后面都是空格来添加，所以每次循环增加 ret 时，先判断是否已经安排完任务了
                if (map[25] == 0) break;
                if (cnt < 26 && map[25 - cnt] > 0) {
                    map[25 - cnt]--;
                }
                ret ++;
                cnt ++;
            }

            // 一趟选完，map 排序、
            Arrays.sort(map);

//            }
        }

        return ret;
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A', 'B','B','B'};
        int n = 50;

        System.out.println(leastInterval(tasks, n));

        System.out.println(leastInterval2(tasks, n));
    }
}
