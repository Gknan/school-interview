package graph;

import java.util.*;

/*
对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。
给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式

该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。

你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。

示例 1:

输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

输出: [1]
示例 2:

输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

输出: [3, 4]
说明:

 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
树的高度是指根节点和叶子节点之间最长向下路径上边的数量。

 */
public class FindMinHeightTrees_310 {

    // 第一种方法太耗时
    /*
    方法二：
    拓扑排序的思想，由外向内一层一层剥皮，知道内部只剩下一个或者2个元素时，就是结果
    入度表：叶泽节点的入度其实是1，不是0 了（有向图是0） 根据 邻接表更新入度表
    邻接列表：双向的，所以每个节点都要存储周边的信息

    借助 队列 BFS
    使用 n 来控制，当n == 1or2 时，结束 BFS，返回 queue 中剩下的元素即可
     */
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {

        // 边界判断一定不能少
        if (n < 2) return Arrays.asList(0);

        // 不需要 set 来记录访问，因为每次放过过的都“删除“了

        // 创建 邻接表
        ArrayList<ArrayList<Integer>> adjanc = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjanc.add(new ArrayList<>());
        }
        
        // 创建入度表
        int [] indrees = new int[n];
        
        // 根据 edges 更新 adjanc
        for (int i = 0; i < edges.length; i++) {
            // [1, 0], 1 节点的下一个是0 0 节点的下一个是 1
            int[] cur = edges[i];
            adjanc.get(cur[0]).add(cur[1]);
            adjanc.get(cur[1]).add(cur[0]);
        }

        // 根据 adjanc 更新下 indrees
        for (int i = 0; i < adjanc.size(); i++) {
            for (int next: adjanc.get(i)) {
                // i 的 下一个是 next;更新 next 的 入度
                indrees[next] ++;
            }
        }

        // 拓扑排序

        Queue<Integer> queue = new LinkedList<>();
        // 第一批叶子节点放入 queue
        for (int i = 0; i < n; i++) {
            if (indrees[i] == 1) {
                // 这里的叶子节点是 1 不是 0
                queue.offer(i);
            }
        }

        int cnt = n;// 记录还剩多少个没删除的节点
        // 遍历
        while (!queue.isEmpty()) {
            // 若 只剩  1or2 个，退出 while
            if (cnt <= 2) break;
//            n --;
            // 一次删除一层
            int num = queue.size();
            while (num -- > 0) {
                Integer cur = queue.poll();

                // 这里才弹出元素 这里删除一个，更新 cnt
                cnt --;

                // 删除 cur 更新 找到 cur 的邻接点，更新邻接点的 indree 表，若 有新的叶子，加入 queue
                for (int next: adjanc.get(cur)) {
                    indrees[next] --;

                    if (indrees[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }

        return new ArrayList<>(queue);

    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};

        System.out.println(findMinHeightTrees2(n, edges));
    }


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        // 以每个节点作为 根，计算对应的树的高度 并记录下来，最后根据记录结果 返回

        // 求树的高度肯定就是 BFS 了 由于是无向图，所以需要个 map 记录 visited
        // 由于这里的 序号不重复，可以直接使用序列标识 Set<Integer>
        // Queue<Integer>
        // 把 edges 信息转化成 ArrayList 保存

        // 无向图使用这种方式不合理
//        ArrayList<ArrayList> edList = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            edList.add(new ArrayList());
//        }

        // 使用矩阵表示
        int[][] mat = new int[n][n];
        // 遍历  更新 mat
        for (int i = 0; i < edges.length; i++) {
            //
            int first = edges[i][0];
            int second = edges[i][1];
            mat[first][second] = 1;
            mat[second][first] = 1;
        }

        // 记录每个节点的树高
        int[] sum = new int[n];

        // 以所有节点为根，统计树高
        for (int i = 0; i < n; i++) {
            sum[i] = help(mat, i);
        }

        ArrayList<Integer> ret = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sum.length; i++) {
            min = Math.min(min, sum[i]);
        }

        for (int i = 0; i < sum.length; i++) {
            if (sum[i] == min) ret.add(i);
        }

        return ret;
    }

    // 以 i 节点为根，的数高
    private int help(int[][] mat, int idx) {

        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(idx);
        queue.offer(idx);
        int ret = 0;

        while (!queue.isEmpty()) {

            ret ++;

            int lastCnt = queue.size();
            // 遍历完这一层所有的节点,并把下一行加入到 queue 中
            for (int i = 0; i < lastCnt; i++) {
                Integer cur = queue.poll();
                // 获取当前节点的所有邻居，加入到 queue中
//                if (!visited.contains(cur)) {
                    // 当前节点没有访问过
                    ArrayList<Integer> neibors = getNeibors(mat, cur);
                    // 把当前节点的所有
                    for (int neibor : neibors) {
                        // 若邻居没有访问过，加入到 queue 中，并记录访问
                        if (!visited.contains(neibor)) {
                            visited.add(neibor);
                            queue.offer(neibor);
                        }
                    }
//                }
            }

        }

        return ret;
    }

    // 根据邻接矩阵求 cur 的 邻接点
    private ArrayList<Integer> getNeibors(int[][] mat, Integer cur) {
        // 第 cur 行 第 cur 列 去找
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < mat.length; i++) {
            // 第 cur 列
            if (mat[i][cur] == 1) set.add(i);
        }

        for (int i = 0; i < mat[0].length; i++) {
            // 第 cur 行找
            if (mat[cur][i] == 1) set.add(i);
        }

        return new ArrayList<>(set);
    }

}
