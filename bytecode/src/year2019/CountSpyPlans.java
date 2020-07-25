package year2019;

/*
请听题：给定N（可选作为埋伏点的建筑物数）、D（相距最远的两名特工间的距离的最大值）以及可选建筑的坐标，计算在这次行动中，大锤的小队有多少种埋伏选择。
注意：
1. 两个特工不能埋伏在同一地点
2. 三个特工是等价的：即同样的位置组合(A, B, C) 只算一种埋伏方法，不能因“特工之间互换位置”而重复使用

分析：
1，暴力法，遍历所有的埋伏方案，筛选掉最远距离小于D的方案 C(N, 3) N^2 时间复杂度
2，优化
遍历位置，对于每个位置，计算该节点作为第一个节点的最大距离，找到大于等于最大距离的位置；
第一个节点到最大距离的位置之间计算 C(n, 3)
进一步优化，不要使用 ArrayList，以为这里固定了个数，所以可以使用数组来完成
 */


import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountSpyPlans {

    public static int count(int N, int D, List<Integer> loc) {

        int ret = 0;
        for (int i = 0; i < loc.size(); i ++) {
            for (int j = i + 1; j < loc.size(); j ++) {
                if (loc.get(j) - loc.get(i) > D) {
                    break;
                }
                for (int k = j + 1; k < loc.size(); k ++) {
                    if (loc.get(k) - loc.get(i) > D) {
                        break;
                    }
                    if (loc.get(k) - loc.get(i) <= D) {
                        ret = (ret + 1) % 99997867;
                    } else {
                        break;
                    }
                }
            }
        }

        return ret;
    }

//    public static int count2(int N, int D, List<Integer> loc) {
//        int ret = 0;
//
//        int j = 0;
//        for (int i = 0; i < loc.size(); i ++) {
//            int maxDis = loc.get(i) + D;
//            j = findEqualAndSmallerIdx(j, loc, maxDis);
//
//            int n = j - i + 1;
//            ret += factorial(n) / (factorial(3) * factorial(n - 3));// 实际上这种方法并不好，因为 n略大时已经不好计算了
//
//        }
//    }

    // CN3 问题固定了 left一个位置，转换成了 CN2 问题
    public static long count2(int N, int D, List<Integer> loc) {
        // fix left, swift window
        long ret = 0;
        int left = 0, right = 2;
        while (left < loc.size() - 2) {
            // move right to maxDistance loc
            while (right < loc.size() && loc.get(right) - loc.get(left) <= D) {
                right ++;
            }
            // left + 1 ~ right -1 at least 2
//            right --; // why wrong ??? long -> int
            if (right - 1 - left >= 2) {
                long num = right - 1 - left; //
                ret += (num * (num - 1) / 2);
                ret %= 99997867;
            }

            left ++;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException, NumberFormatException {
//        ArrayList<Integer> list = new ArrayList();
//        for (int i = 1; i <= 1000000; i ++) {
//            list.add(i);
//        }
//
//        System.out.println(count(4, 3, list));
//        System.out.println(count2(4, 3, list));

        ArrayList<Integer> list = new ArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstLine = br.readLine();
        String[] firstLineStrs = firstLine.split(" ");
        int N = Integer.parseInt(firstLineStrs[0]);
        int D = Integer.parseInt(firstLineStrs[1]);

        String secondLine = br.readLine();
        String[] secondLineStrs = secondLine.split(" ");

        assert secondLineStrs.length == N;

        for (int i = 0; i < N; i ++) {
            list.add(Integer.parseInt(secondLineStrs[i]));
        }

        System.out.println(count2(N, D, list));


    }
}
