package top100;

import basics.ListNode;

import java.util.*;

/*
给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

示例 :
给定 a / b = 2.0, b / c = 3.0
问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]

输入为: vector<pair<string, string>> equations, vector<double>& values,
vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，
即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

基于上述例子，输入如下：

equations(方程式) = [ ["a", "b"], ["b", "c"] ],
values(方程式结果) = [2.0, 3.0],
queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。

分析：
1，把原来数组处理，如 a,b   b,c  [2, 3] 经过处理添加 b,a  a,a  b,b  cb, cc [0.5  1  1  1/3  1] 但是这里直接 处理后放进去，出在丢失精度的问题
怎么保存才能不丢失呢 那就使用 分子分母 两个数字表示一个整数 索引，原来就 values 表示成 [[2.0, 1], [3.0, 1]]

2，保存之后，需要计算时，从 保存的组合中选，若若干个能拼接成 axxxxxc 的形式，怎是计算结果

3，使用 HashMao<String, Map<String, Double>> 类型保存数据
这里其实是把问题抽象成有向图，找解的过程就是 从起点找终点的过程
分为两步，构建有向图，这里使用 Map 存储 节点和边的信息

搜索的过程，记录经理的边的乘积，防止重复搜索，set 保证不重复




 */
public class CalcEquation_399 {

    HashMap<String, HashMap<String, Double>> map = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values, List<List<String>> queries) {
//        HashMap<String, HashMap<String, Double>> map = new HashMap<>();

        int i = 0;
        for (List<String> eles : equations) {
            if (!map.containsKey(eles.get(0))) {
                HashMap<String, Double> map1 = new HashMap<>(1);
                map1.put(eles.get(1), values[i]);
                map.put(eles.get(0), map1);
            } else {
                map.get(eles.get(0)).put(eles.get(1), values[i]);
            }

            if (!map.containsKey(eles.get(1))) {
                HashMap<String, Double> map2 = new HashMap<>(1);
                map2.put(eles.get(0), 1 / values[i]);
                map.put(eles.get(1), map2);
            } else {
                map.get(eles.get(1)).put(eles.get(0), 1 / values[i]);
            }

            i++;
        }

//        System.out.println(map);

        double[] ret = new double[queries.size()];
        for (int j = 0; j < queries.size(); j++) {
            //
//            ret[i] = queryProcess(queries.get(j));
            ret[j] = queryProcess(queries.get(j));
        }

        return ret;
    }

    // BFS 查找 path 的路径
    private double queryProcess(List<String> path) {
        // 处理特殊情况
        if (!map.containsKey(path.get(0)) || !map.containsKey(path.get(1)))
            return -1.0;
        // 相等
        if (path.get(0).equals(path.get(1))) return 1.0;

        // BFS
        Queue<String> queue = new LinkedList<>();
//        LinkedList<String> visited = new LinkedList<>();
        HashMap<String, Double> visited = new HashMap<>();
        queue.offer(path.get(0));
        visited.put(path.get(0), 1.0);

        while (!queue.isEmpty()) {
            // 遍历当前层的节点
            String poll = queue.poll();

            // 去重
            HashMap<String, Double> neibors = map.get(poll);
            // 遍历所有邻居
            for (String key : neibors.keySet()) {
                // 已经访问过
                if (visited.containsKey(key)) continue;

                // 没有访问过，访问，更新 visit queue
                queue.offer(key);
                visited.put(key, neibors.get(key) * visited.get(poll));

                if (key.equals(path.get(1))) return visited.get(key);
            }
        }


//        return 0.0;
        return -1.0;
    }

    public static void main(String[] args) {
        CalcEquation_399 cal = new CalcEquation_399();
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "c")
        );
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        System.out.println(equations);
        System.out.println(queries);

        System.out.println(Arrays.toString(cal.calcEquation(equations, values, queries)));
    }
}
