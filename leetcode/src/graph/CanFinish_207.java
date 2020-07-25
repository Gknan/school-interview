package graph;

import java.util.*;

/*
你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？



示例 1:

输入: 2, [[1,0]]
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。  1 是本位， 0 是前置
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。


提示：

输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
1 <= numCourses <= 10^5

分析：
如果图中存在环路，那么返回 false
如果图中不存在环路，返回 true
DFS 遍历图，若遍历完都没有环路，返回true；中间出现环路，返回  false

可能存在有的课程不需要先修课程

超时，怎么优化？
mem HashMap<> 记录当前节点开始，是否可通行

拓扑序列：（原名是拓扑排序）
用来解决有向无环图的问题   设计的排课表，安排计算，任务之间有先后关系的问题；按照拓扑排序得到的是一个可执行的熟悉
过程是：
In[] 数组记录每个节点的入度；
初始化；
q 存放当前找到可完成的节点
遍历所有节点，找到入度为 0 的节点，更改当前节点的吓一跳节点的 入度 - 1；加入到 q；
重复上满的过程直到 q.size = 节点数
若其中存在环怎么办？怎么判断；若其中存在环，某一次遍历中，明名还有节点，但是找不到入度为 0 的节点




 */
public class CanFinish_207 {

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 存储所有节点的下一条 下标是节点号，所有下一跳是 list
        ArrayList<ArrayList<Integer>> in = new ArrayList<>();
        // 存放所有节点的入度
        int[] indegre = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            in.add(list);
        }
        // bulid in table
        for (int i = 0; i < prerequisites.length; i++) {
            int[] cur = prerequisites[i];
            in.get(cur[1]).add(cur[0]); // 后一个是前一个的前置
            // 维护入度表
            indegre[cur[0]] ++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // 所有为入度为 0 的节点加入队列
        for (int i = 0; i < numCourses; i++) {
            if (indegre[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 删除该改节点
            numCourses --;
            // 修改下一跳的 入度
            for (int next: in.get(cur)) {
                indegre[next] --;
                if (indegre[next] == 0) {
                    // 新的入度为 0 的节点，加入到 quue 中
                    queue.offer(next);
                }
            }
        }

        return numCourses == 0;
    }

    static HashMap<Integer, Boolean> mem;
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 根据 prerequisites 创建邻接矩阵
        if (numCourses == 0) return true;
        int[][] matrix = new int[numCourses][numCourses];

        // build matrix use prereueistes
        for (int i = 0; i < prerequisites.length; i++) {
            int[] cur = prerequisites[i];
//            matrix[cur[0]][cur[1]] = 1;
            matrix[cur[1]][cur[0]] = 1;

        }

        mem = new HashMap<>();

        // matrix 中值为 0 位不可达，值为1 表示可达
        // 从 根 开始 dfs
        boolean flag = true;
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            visited.add(i);
            if (!dfs(matrix, i, visited)){
                flag = false;
                break;
            }
            mem.put(i, true);
            visited.remove(i);
        }
        return flag;
    }

    private static boolean dfs(int[][] matrix, int idx, HashSet<Integer> visited) {
        // 产生环路
//        if (visited.contains(idx)) return false;

        boolean flag = true; // 有返回值的函数，一定要在最后抛出结果
        // 第 idx 行就是临接点
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[idx][i] != 0) { // idx 节点和 i 节点存在边
                if (visited.contains(i)) {
                    flag = false;
                    break;
                }
                if (mem.containsKey(i)) {
                    flag = true;
                    mem.put(i, true);
                    break; // 若当前节点可达，剪枝
                }
                visited.add(i);
                flag = dfs(matrix, i, visited);
                visited.remove(i);
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}, {0,1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }

}
