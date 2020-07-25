package year2019;

/*
题目描述
小明目前在做一份毕业旅行的规划。打算从北京出发，分别去若干个城市，然后再回到北京，每个城市之间均乘坐高铁，且每个城市只去一次。
由于经费有限，希望能够通过合理的路线安排尽可能的省一些路上的花销。给定一组城市和每对城市之间的火车票的价钱，找到每个城市只访问一次并返回起点的最小车费花销。

输入描述:
城市个数n（1<n≤20，包括北京）

城市间的车票价钱 n行n列的矩阵 m[n][n]
输出描述:
最小车费花销 s

分析：
1，使用无记忆化的方法先求出再优化；宽度优先搜索

优化：
1,当前找到的最小花费如果小于某个分支，不用继续该分指；因为 距离不能为负数
2,记忆搜索；动态规划；每次新的分支都要计算一遍，搜索过程中保存搜索信息
3 有重复计算的一定可以改成动态规划

实际上是最小生成树；求得最小生成树后再累加回到结果的值；但是最终结果会受到最后一个点到原点的值的影响

以上是经典的旅行商问题，TSP，回溯法和动态规划法两种方案；上面的回溯法已经完成，且经过剪枝优化，但是没有AC；接下来分析动态规范法

假设 4 个城市 1 2 3 4
从城市1 出发，记一个二维数组为 dp[i]{V} 标识从i出发，经过V集合中的所有带你回到 1 的最小值
需要求得是  dp[1]{2,3,4} = min(dist(1,2) + dp[2]{3,4}, dist(1, 3) + dp[3]{2,4}, dist(1,4)+dp[4]{2,3})
上面 dist(1,2)可以直接得到
dp[2]{3,4} = min(dist(2,3)+dp[3]{4},dist(2,4)+dp[4]{3})
dp[3]{4} = dist(3,4) + dp[4]{}
dp[4]{} = dsit(4,1)
怎么表达 集合V，这里使用二进制表达，如n=4，1010 表示的是{1,3}

经典旅行商问题，怎么通过 回溯方法推导动态规划方法
每一步的决策会把该问题转化成求子问题，子问题求解完成，该问题解决
还有个难点是集合形式怎么转换成二进制的表示

 */

import java.util.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TravalProblem {

    // DP method
    private static int calCostDP(int[][] cost) {
        int n = cost.length;
        int V = 1 << (n - 1); //

        int[][] dp = new int[n][V];

        // set initrial value
        for (int i = 0; i < n; i ++) {
            dp[i][0] = cost[i][0];
        }

        // fill dp
        for (int i = 1; i < V; i ++) {// col
            for (int j = 0; j < n; j ++) { // row
                dp[j][i] = Integer.MAX_VALUE; // set most max

                // (x >> (i - 1) & 1) == 1 means x's the ith is 1
                if (((i >> (j - 1)) & 1) == 1) { // 1 >> -1 = 0
                    continue;
                }
                for (int k = 1; k < n; k ++) {
                    // i >> (k - 1) if k in V, than i's the kth is 1, after formula, we move the 1 to the last bit
                    // so ((i >> (k - 1)) & 1) == 1 means V has k.
                    if (((i >> (k - 1)) & 1) == 1) { // city k in V, select k as new
                        dp[j][i] = Math.min(dp[j][i], cost[j][k] + dp[k][i ^ (1 << (k - 1))]);
                        // i ^ (1 << (k - 1)) means V' that is V substract k
                        // 1 << (k - 1) means the kth 1 , that is V has city k
                        // i ^ (1 << (k - 1)) means we first has k in V, but now we delete k from V
                    }
                }
            }
        }

        //
        return dp[0][V - 1];
    }


    public static int minCost = Integer.MAX_VALUE;

    private static int calCost(int[][] cost) {
        int n = cost.length;
//        int[] visited = new int[n];
        HashSet<Integer> visited = new HashSet<>();

        // start form loc 0
        visited.add(0);
        process(0, visited, 0, cost);

        return minCost;
    }

    // DFS
    private static void process(int preCity, HashSet<Integer> visited, int preSumCost, int[][] cost) {
        // back to loc 0
        if (visited.size() == cost.length) {
            // update minCost
            minCost = Math.min(minCost, preSumCost + cost[preCity][0]);// preSumCost + cost[0][preCity] lastly need back to loc 0
            // lack return
            return;
        }

        //
        for (int i = 0; i < cost.length; i ++) {
//            if (!visited.contains(i)) {
            if (i != preCity && minCost > preSumCost && !visited.contains(i)) { // minCost > preSumCost  cut leaf
                visited.add(i);// add i
//                preSumCost += cost[preCity][i];
//                preCity = i;
                process(i, visited, preSumCost + cost[preCity][i], cost);
                // recover state
                visited.remove(i);
                // bug also need to recover preSumCost, cause process fun need this as a param, BYW, preCity should keep or recover
//                preSumCost -= cost[preCity][i];
            }
        }
    }

    public static void main(String[] args) throws IOException, NumberFormatException {
        // for test
//        int[][] cost = {
//                {0, 2, 6, 5},
//                {2, 0, 4, 4},
//                {6, 4, 0, 2},
//                {5, 4, 2, 0}
//        };
//        int[][] cost = {
//                {0, 3, 4, 2, 7},
//                {3, 0, 4, 6, 3},
//                {4, 4, 0, 5, 8},
//                {2, 6, 5, 0, 6},
//                {7, 3, 8, 6, 0}
//        };// 0 1 4 3 2 0  19
//
//        System.out.println(calCostDP(cost));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i ++) {
            String[] numStrs = br.readLine().split(" ");
            for (int j = 0; j < n; j ++) {
                cost[i][j] = Integer.parseInt(numStrs[j]);
            }
        }

        System.out.println(calCost(cost));

//        System.out.println(1 >> -1);
    }
}
