package top100;


import java.util.*;

/*
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.

分析：
1，暴力法，从 根号n 开始向下遍历，遍历到一个位置，加入到组成成分之中后，继续往下，知道得到结果或者不能得到结果
回溯
2，计算得到 1~ Math.floor(根号n) 的所以结果，保存到 数组 中
按照贪心策略，从最大的开始选择，往下选择即可
时间 O()
能剪枝吗？ 注意剪枝的位置以及剪枝后的处理，是否需要回复现场


3，BFS，最开始是 13，经过 分别减去 (1 4 9) 之后
第二层的节点时 12 9  4 然后第二层的每个节点依旧是减去 1 4 9
12 的孩子书 (11 8 3) 9 的孩子(8 5  0) 当出现 0 时，由于是 BFS，说明当前得到了最短的路径，结束返回
使用队列来进行宽度搜索；过程中需要记录当前层数
问题，怎么来更新 level ；按行来操作，根入队，队列不为空，level ++ ; len = len(queue) 处理完 len 中所有的
点之后再从 queue 中进入下一个循环

BFS 方法优化，因为比如相同的非叶子节点这时候就不需要添加所有的节点，只需要一个就可以。使用 set 来去重

// 由暴力到动态规划


 */
public class NumSquares_279 {

    // DP 先把 dp 数组较小的位置计算完毕，然后计算dp[n]
    // dp[i] 0<=i<=n  表示 i 得到的最少组成个数
    // 递推公式：dp[i] = Math.min(dp[i], dp[i - j*j] + 1)
    // 初始化时，dp[i] == i 因为每个位置的最大值是 i 个 1 组成
    // 返回的是 dp[n]
    public static int numSquares6(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        // init
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }

        // update
        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j ++) {
            for (int j = 1; i - j * j >= 0; j++) {
//                if (i - j * j >= 0) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
//                }
            }
        }

        return dp[n];
    }


    // 暴力 + 备忘录
    public static int numSquares5(int n) {
        if (n <= 1) return n;

        int[] mem = new int[n + 1];

        return process3(n, mem);
    }

    // 返回 得到 n 的最小平法和的个数
    private static int process3(int n, int[] mem) {
        if (mem[n] != 0) return mem[n];

        if (n <= 0) return 0;
//        if (n == 0) return 0;
//        if (n == 1) return 1;

        int ret = Integer.MAX_VALUE;
        int up = (int) Math.sqrt(n);
//        if (up * up == n) return 2;
        if (up * up == n) {
            mem[n] = 1;
            return 1; // 如 16 返回的应该是1
        }
        for (int i = 1; i <= up; i++) {
            ret = Math.min(ret, 1 + process3(n - i * i, mem));
            // update mem
            mem[n] = ret;
        }

        return ret;
    }

    // 最原始的暴力递归
    public static int numSquares4(int n) {
        if (n <= 1) return n;

        return process2(n);
    }

    // 返回 得到 n 的最小平法和的个数
    private static int process2(int n) {
        if (n <= 0) return 0;
//        if (n == 0) return 0;
//        if (n == 1) return 1;

        int ret = Integer.MAX_VALUE;
        int up = (int) Math.sqrt(n);
//        if (up * up == n) return 2;
        if (up * up == n) return 1; // 如 16 返回的应该是1
        for (int i = 1; i <= up; i++) {
            ret = Math.min(ret, 1 + process2(n - i * i));
        }

        return ret;
    }

    // 二叉树方法优化
    public static int numSquares3(int n) {
        if (n <= 1) return n;

        int len = (int) Math.sqrt(n);
        // help array
        int[] help = new int[len];
        for (int i = 0; i < len; i++) {
            help[i] = (int) Math.pow((i + 1), 2);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(n);
        int level = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(n);

        while (!queue.isEmpty()) {
            // 怎么更新 level
            level++;
//            int top = queue.poll();
            int curLen = queue.size();
            for (int i = 0; i < curLen; i++) {
                // 当前行第 i 个节点进行处理
                int top = queue.poll();
                for (int j = 0; j < len; j++) {
                    int k = top - help[j];
                    if (k < 0) break; // help 是从小到大的，若当前的根减去小的树已经为负数，那么减去更大的数字一定为负
                    if (k == 0) return level;
                    // 优化，若已经有相同的根节点，若不加入
                    if (!set.contains(k)) {
                        queue.add(k);
                        // update set
                        set.add(k);
                    }
                }
            }
        }

        return level;

    }

    // BFS 方法
    public static int numSquares2(int n) {

        if (n <= 1) return n;

        int len = (int) Math.sqrt(n);
        // help array
        int[] help = new int[len];
        for (int i = 0; i < len; i++) {
            help[i] = (int) Math.pow((i + 1), 2);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(n);
        int level = 0;

        while (!queue.isEmpty()) {
            // 怎么更新 level
            level++;
//            int top = queue.poll();
            int curLen = queue.size();
            for (int i = 0; i < curLen; i++) {
                // 当前行第 i 个节点进行处理
                int top = queue.poll();
                for (int j = 0; j < len; j++) {
                    int k = top - help[j];
                    if (k < 0) break; // help 是从小到大的，若当前的根减去小的树已经为负数，那么减去更大的数字一定为负
                    if (k == 0) return level;
                    queue.add(k);
                }
            }
        }

        return level;

    }


    static int cnt = Integer.MAX_VALUE;

    public static int numSquares(int n) {
        if (n <= 1) return n;

        int len = (int) Math.sqrt(n);
        // help array
        int[] help = new int[len];
        for (int i = 0; i < len; i++) {
            help[i] = (int) Math.pow((i + 1), 2);
        }

        // list 主要做了增删改，所以使用 LinkedList 效率会更好一些
        LinkedList<Integer> list = new LinkedList<>();
        // greed select
        process(help, len - 1, n, list);

        return cnt;
    }


    // 回溯找结果
    // 回溯的化遍历了所有的情况，没有考虑到贪心策略
    private static void process(int[] help, int start, int preLeft, LinkedList<Integer> list) {
        if (preLeft < 0) return;
        if (preLeft == 0) {
//            System.out.println(list);
            cnt = Math.min(cnt, list.size());
            return;
        }

        for (int i = start; i >= 0; i--) {
            if (list.size() >= cnt - 1) break;
            list.add(help[i]);
            // 剪枝 若当前找到的个数已经超过当前找到的最小值，推出循环
//            if (list.size() >= cnt) {
//                break;
            // bug break 之前没有还原list
//            }
            process(help, start, preLeft - help[i], list);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
//        System.out.println(Math.sqrt(12));
//        System.out.println((int)Math.sqrt(12));

        int n = 12;

        System.out.println(numSquares(n));
        System.out.println(numSquares2(n));
        System.out.println(numSquares3(n));
        System.out.println(numSquares4(n));
        System.out.println(numSquares5(n));
        System.out.println(numSquares6(n));

    }
}
