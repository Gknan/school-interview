package backtracking;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.LinkedList;

/*
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"

分析：
找出所有的全排列并存储，需要查找直接返回

求全排列 按照顺序

 */
public class GetPermutation_60 {


    // 生成的树一定是满二叉树，且 每个元素对应的 的子树的 有效值节点个数是知道的
    // 比如 i 后面有 n-1 个值，那么以 i 为首的有效解一定有 n-1 ! 个
    // 若 k < n-1! 那么重 i 开始深度搜；否则，更新 k = k - n-1 ！ 继续找新的一定在解路径上的点

    public String getPermutation2(int n, int k) {
        boolean[] visited = new boolean[n + 1];
        int [] fac = new int[n + 1];

        // 阶乘数组
        fac[0] = 1; // 0!= 1
        for (int i = 1; i <= n; i++) {
            fac[i] = i * fac[i - 1];
        }

        ArrayList<Integer> preList = new ArrayList<>();
        dfs(n, k, 1, visited, fac, preList);

        // 根据 preLsit 重建结果
        StringBuilder sb = new StringBuilder();
        for (int ch: preList) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private void dfs(int n, int cnt, int idx, boolean[] visited, int[] fac, ArrayList<Integer> preList) {
        if (cnt == 0) {
            // 收集结果
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
//            cnt -= fac[i];
            if (cnt - fac[n - preList.size() - 1] > 0) {
                cnt -= fac[n - preList.size() - 1];
                continue; // 当前剩余的根之后的节点 剪枝
            }

            visited[i] = true;
            preList.add(i);
            dfs(n, cnt, idx, visited, fac, preList);
            // 找到的第一个解就是返回的解，不再进行恢复现场了
//            preList.remove(preList.size() - 1);
//            visited[i] = false;
        }
    }

    //    ArrayList<String> list;
    int cnt = 0;
    String ret;
    public String getPermutation(int n, int k) {

        if (n <= 0) return "";

//        list = new ArrayList<>();

        boolean[] visited = new boolean[n + 1];
        backtrack(n, 1, visited, k, new LinkedList<Character>());

//        System.out.println(list);
//        return list.get(k - 1);

        return ret;
    }

    private void backtrack(int n, int idx, boolean[] visited, int k, LinkedList<Character> preStr) {

        // 剪枝
        if (ret != null) return;

        if (idx > n) {
            // 收集结果
            StringBuilder sb = new StringBuilder();
            for (char c: preStr) {
                sb.append(c);
            }
//            list.add(sb.toString());
            cnt ++;
            if (cnt == k) ret = sb.toString();

            // 一定是结束条件 加 返回
            return;
        }

        for (int begin = 1; begin <= n; begin++) {
            if (visited[begin]) continue;

            visited[begin] = true;
            // 数字转 char char 转数字 Character.isDigit(ch)
            preStr.add((char)(begin + '0'));
            backtrack(n, idx + 1, visited, k, preStr);
            preStr.removeLast();
            visited[begin] = false;
        }

    }

    public static void main(String[] args) {
        GetPermutation_60 test = new GetPermutation_60();

        System.out.println(test.getPermutation(3, 3));
        System.out.println(test.getPermutation2(3, 3));
    }
}
