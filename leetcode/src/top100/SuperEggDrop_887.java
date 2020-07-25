package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
你的目标是确切地知道 F 的值是多少。
无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？

示例 1：
输入：K = 1, N = 2
输出：2
解释：
鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
如果它没碎，那么我们肯定知道 F = 2 。
因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
示例 2：

输入：K = 2, N = 6
输出：3
示例 3：

输入：K = 3, N = 14
输出：4

提示：

1 <= K <= 100
1 <= N <= 10000

分析：
1，要求最少的次数确定 F 值；直接的思维就是二分法，每次根据鸡蛋的碎否缩减一半空间，再另外一半空间里找解
2，也就是 F 的取值范围 0 <= F <= N ，我们要在最查的时间内找到它。
3，每次尝试从存在解的区域的中间找
4，循环退出的条件是
每次选择注意的事情，区间里有偶数的，选中间偏右，作为划分点，奇数个就选中即可；这样做的目的是使用 给的新 F<= N
所以其实求得是扩展出来的这颗树的深度；这里是考虑了鸡蛋肯定是满足条件的情况；没有考虑鸡蛋的情况
如果仅仅是这样的话，只需要求树的高度
什么时候确定值呢？左边界等于边界，只有一个元素

1 3 没有通过的 case，按照步数最少，存在 鸡蛋不够用的情况，这时候就退化成了从底层开始试的问题
最少步数需要的最少鸡蛋个数就是搜索树的最左边界的长度
所以，先求出最少鸡蛋的个数，然后按照搜索树来搜索，剩下一个鸡蛋时，在搜索区间中从小到大尝试

还需要一个信息，碎了 k- 1 个鸡蛋时，已经走了多少步
方法2时间复杂度高，备忘录优化
时间复杂度 子问题个数 * 每个子问题的复杂度 = K*N * N

另一种思路，给你 K 个鸡蛋，可以扔 M 次，求最多可以决定的楼层



 */
public class SuperEggDrop_887 {

    public int superEggDrop4(int K, int N) {

        int[][] dp = new int[K + 1][N + 1];// m 最大不会超过 N

        int m = 0;

        while (dp[K][m] < N) {
            // 状态转移方程
            m ++;
            for (int i = 1; i <= K; i++) {
                dp[i][m] = dp[i][m - 1] + dp[i - 1][m - 1] + 1;
                // 当前扔了一次，所以m-1; dp[i][j] 表示的是 i 个鸡蛋，最多扔 j 次，最大可以决定的楼层
            }
        }

        return m;
    }


    int [][] mem;
    // 备忘录
    public int superEggDrop3(int K, int N) {

        mem = new int[K + 1][N + 1];

        return helper(K, N);
    }

    private int helper(int K, int N) {

        if (mem[K][N] != 0) return mem[K][N];

        // 边界判断
        if (N == 0) return 0;// 若当前只有一层，只能是0
//        if (K == 1) return N - 1; // 若只有一个鸡蛋，只能从下往上依次找 找到 N - 1 位置就可以
        if (K == 1) {
            mem[K][N] = N;
            return N;
        }

        int ret = Integer.MAX_VALUE;
        int j = 1;
        for (; j <= N; j ++) {
            // 在 j 层扔了一个鸡蛋，扔完鸡蛋后，只能走一边，即空间变小了，但是扔鸡蛋的次数由扔鸡蛋次数最多的那一边决定，所以内层是 max
            // 外层的min ，手握 K（K>1） 个蛋，所有楼层都试一遍，那个楼层的解最小，就选哪个
            ret = Math.min(Math.max(helper(K, N - j), helper(K - 1, j - 1)) + 1, ret);
        }

        mem[K][N] = ret;
        return mem[K][N];
    }


    // DP
    /*
    dp:状态 (K,N)
    dp【i,j] 表示当前有k个蛋，楼层有 N 的时候，最少的扔蛋次数
    转移方程： dp[i,j] = min(dp[i-1][j-1], d[i][N - j])
    站在 j 层，扔鸡蛋，两种结果，鸡蛋碎了，对应 dp[i - 1][j - 1] 鸡蛋没碎 对应 dp[i][N -j]
    dp[K, N] 面对 K 个鸡蛋，N 层楼，的最优值
     */
    public int superEggDrop2(int K, int N) {
        // 边界判断
        if (N == 0) return 0;// 若当前只有一层，只能是0
//        if (K == 1) return N - 1; // 若只有一个鸡蛋，只能从下往上依次找 找到 N - 1 位置就可以
        if (K == 1) return N;

        int ret = Integer.MAX_VALUE;
        int j = 1;
        for (; j <= N; j ++) {
            // 在 j 层扔了一个鸡蛋，扔完鸡蛋后，只能走一边，即空间变小了，但是扔鸡蛋的次数由扔鸡蛋次数最多的那一边决定，所以内层是 max
            // 外层的min ，手握 K（K>1） 个蛋，所有楼层都试一遍，那个楼层的解最小，就选哪个
            ret = Math.min(Math.max(superEggDrop2(K, N - j), superEggDrop2(K - 1, j - 1)) + 1, ret);
        }

        return ret;
    }

//    int max = Integer.MIN_VALUE;

    // key: 当前剩余鸡蛋树； value: F 的取值区间上下界限  向下搜索的过程中更新 map
//    HashMap<Integer, ArrayList<ArrayList>> map;

    // key value(key: 知道当前用了 K 个鸡蛋，走了多少步，对应的剩余区间是什么)
//    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> map;

    // 剩余鸡蛋相同时，走的步数肯定不同，所以 key 相同，value 被覆盖是不可接收的
    HashMap<Integer, ArrayList<HashMap<Integer, ArrayList<Integer>>>> map;

    public int superEggDrop(int K, int N) {

        map = new HashMap<>();
//        int[] arr = process(0, 0, N);// F 的取值范围是 0~14
        int[] arr = process(0, 0, N, 0);// F 的取值范围是 0~14
        if (arr[1] <= K) {
            // 给的鸡蛋数足够
            return arr[0];
        } else {
            int ret = 0;
            // 给的鸡蛋数不够；找到碎了 K - 1个鸡蛋的位置，搜索区间
            ArrayList<HashMap<Integer, ArrayList<Integer>>> hashMaps = map.get(K - 1);
            // 求所有分指中的最大值
            for (HashMap<Integer, ArrayList<Integer>> integerArrayListHashMap: hashMaps) {
                // 当前分支中求最大值 即需要的最长步数
                int max = 0;
                for (Integer key : integerArrayListHashMap.keySet()) {
                    // 使用了 K - 1 个鸡蛋走的步数
                    max += key;
                    ArrayList<Integer> boader = integerArrayListHashMap.get(key);
                    // 遍历boader 对应的区间
                    max += boader.get(1) - boader.get(0); // 若 [1,2,3] 在判断是，是完 1 2 就可确定结果了
                }
                ret = Math.max(ret, max);
            }

            return ret;
        }
    }


    /**
     *
     * @param k 当前使用了多少鸡蛋
     * @param left F 的左边界
     * @param right F 的右边界
     * @param preStep 从根开始走了多少步
     * @return
     */
    private int[] process(int k, int left, int right, int preStep) {
        // 更新 map
        ArrayList<HashMap<Integer, ArrayList<Integer>>> k2List = map.getOrDefault(k, new ArrayList<HashMap<Integer, ArrayList<Integer>>>());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        HashMap<Integer, ArrayList<Integer>> step2Boader = new HashMap<>();
        step2Boader.put(preStep, list);
        k2List.add(step2Boader);
        map.put(k, k2List);

        if (left == right) {// 只有一层楼，F 的取值范围就是 left = right 所以不用判断就知道结果
            return new int[]{0, 0};
        }

        int mid = left + (right - left + 1) / 2;
        int[] leftBranch = process(k + 1, left, mid - 1, preStep + 1); // 选择 mid ，鸡蛋碎了 需要一个鸡蛋
        int[] rightBranch = process(k, mid, right, preStep + 1); // mid ，鸡蛋没碎

        // 左右分指的最大值 加上 这一步选择的 mid
        int len = Math.max(leftBranch[0], rightBranch[0]) + 1;
        return new int[]{len, leftBranch[1] + 1};
    }

    public static void main(String[] args) {
        SuperEggDrop_887 test = new SuperEggDrop_887();
        int K = 2;
        int N = 9;
        System.out.println(test.superEggDrop(K, N));
    }

    // 返回数组 arr[] arr[0] 是当前树的高度，arr[1]是当前树的最左边界长度（需要最多鸡蛋个数）

    /**
     * @param k 需要的鸡蛋个数
     * @param left
     * @param right
     * @return
     */
//    private int[] process(int k, int left, int right) {
//        // 更新 map
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(left);
//        list.add(right);
//        ArrayList<ArrayList> curList = map.getOrDefault(k, new ArrayList<>());
//        curList.add(list);
//        map.put(k, curList);
//
//        if (left == right) {// 只有一层楼，F 的取值范围就是 left = right 所以不用判断就知道结果
//            return new int[]{0, 0};
//        }
//
//        int mid = left + (right - left + 1) / 2;
//        int[] leftBranch = process(k + 1, left, mid - 1); // 选择 mid ，鸡蛋碎了 需要一个鸡蛋
//        int[] rightBranch = process(k, mid, right); // mid ，鸡蛋没碎
//
//        // 左右分指的最大值 加上 这一步选择的 mid
//        int len = Math.max(leftBranch[0], rightBranch[0]) + 1;
//        return new int[]{len, leftBranch[1] + 1};
//    }
}
