package top100;

import java.util.HashMap;

/*
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

法1：
DP法：
G(n) 表示1~n 个节点组成的二叉搜索树个数
F(i, n) 表示以 i 为根的搜索树个数
那么 G(n) = ∑ F(i, n)
举例子： F(3, 7) 表示求 3 为根的搜索树个数；F(3,7) = G(2) * G(7-3)
所以：F(i, n) = G(i -1)* G(n - i)

法2：
数学公式法
卡特兰数：
C0 = 1， Cn+1 = 2(2n + 1)*Cn/n+2

 */
public class NumTrees_96 {

    public int numTrees3(int n) {
        if(n == 0) return 1;

        return getAns(1, n);
    }

    private int getAns(int start, int end) {
        int ans = 0;

        if (start >= end) return 1;

        for (int i = start; i <= end; i ++) {
            int leftNums = getAns(start, i - 1);
            int rightNums = getAns(i + 1, end);

            // 左子树右子树组合
            ans += leftNums * rightNums;
        }

        return ans;

    }

    // 参数 i 与 getAns 的结果有关，所以可以由n 代替 end 和 start
    // 返回 n 个数对应的搜索数数量
    private int getAns2(int n) {
        int ans = 0;

        if (n == 0 || n == 1) return 1;

        for (int i = 1; i <= n; i ++) {
            int leftNums = getAns2(i - 1);
            int rightNums = getAns2(n - i);

            // 左子树右子树组合
            ans += leftNums * rightNums;
        }

        return ans;

    }

    // 记忆化搜索过程
    private int getAns3(int n, HashMap<Integer, Integer> memorization) {
        if (memorization.containsKey(n)) {
            return memorization.get(n);
        }

        int ans = 0;
        // 备忘录中没有需要计算
        if (n == 0 || n == 1) return 1;

        for (int i = 1; i <= n; i ++) {
            int leftNums = getAns2(i - 1);
            int rightNums = getAns2(n - i);

            // 左子树右子树组合
            ans += leftNums * rightNums;
        }

        // 添加到备忘录
        memorization.put(n, ans);
        return ans;

    }

    // DP
    public int numTrees(int n) {
        int [] G = new int[n + 1];

        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; i ++) {
            // 目前的 n = i
//            int gi = 0;
            for (int j = 1; j <= i; j ++) { // F(i, n)
//                gi += G[j - 1] * G[i - j];
                G[i] += G[j - 1] * G[i - j];
            }
//            G[i] = gi;
        }

        return G[n];
    }

    public int numTrees2(int n) {

        // overflow problem
        long ret = 1;
        for (int i = 1; i <= n; i ++) {
            ret = ret * 2 * (2*i -1) / (i + 1);
        }

        return (int)ret;
    }

}
