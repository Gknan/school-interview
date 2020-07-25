import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/*
题目描述：

1 ~ 10 4 组
分出一些 给 别人 比如 n 张
别人可以按照 单 对 顺子 的方式出牌
这里要注意的是顺子不能超过 5
最少多少次可以出完所有的牌

输入：
A1  A2  A3  ... A10
分别代表 1的个数，2 的个 ... 10 的个数
A1 ~A10 不全部为0

输出描述：最少的组合个数

1 1 1 2 2 2 2 2 1 1

输出 3

分析：
1，贪心策略，每次尽量组成顺子，然后再去处理剩下的    11 22 33 44 5 6 7 8这里 这里的情况是就要
考虑 顺子的开头位置 显然要选的是 1 开头
2，选顺子的开头位置，只能在 1~6 里面选，选的时候，要尽量选单着连在一起的，不破坏对子
所以可以采用，遍历一遍统计

dp
dp[i] 表示 截止 第 i 大的牌时，找到的最少出牌数字   o<i<10
arr[i - 1] = 0  dp[i] = dp[i - 1] + arr[i] + 1/2 不连续，只能单出或双出
arr[i - 1] != 0
       arr[]

arr[i] == 0  dp[i] = dp[i -1]
arr[i] != 0
    arr[i -1] == 0  dp[i] = dp[i -1] + arr[i] + 1/2
    arr[i - 1] != 0 dp[i] = 与前面组成顺子, 与前面组成对子，单独出 三者的最小值

与前面组成顺子 dp[i][]
与前面组成对子 dp[i - 1][j - 1]
dp[i][j] 表示当前i位置有 j 个元素时最少出牌个数
dp[i] =

返回 dp[m][输入的值]


贪心：
先判断是顺子，再判断对子，最后判断单牌

传参记录当前的步数
remain 可以记录当前得到


 */
public class PlayCard {

    public static void main(String[] args) throws IOException {
//        BufferedReader br =
//                new BufferedReader(new InputStreamReader(System.in));
//
//        String [] inputs = br.readLine().split(" ");
//        int[] arr = new int[10];
//        int remain = 0;
//        for (int i = 0; i < 10; i++) {
//            arr[i] = Integer.valueOf(inputs[i]);
//            remain += arr[i];
//        }

        // for test
        int[] arr = {1, 1, 1, 2, 2, 2, 2, 1, 1, 1};
        int remain = 0;
        for (int i = 0; i < arr.length; i++) {
            remain += arr[i];
        }
//        System.out.println(Arrays.toString(arr));

//        findAnwser(arr);
        new PlayCard().findAnwser(arr, remain);
        System.out.println(ret);
    }

    // remain 记录当前还有多少张牌没出
    private void findAnwser(int[] arr, int remain) {
        // 按照每次操作后 状态减 - 1 -2 等来分析，直到找到一组答案，更新 操作数
        process(arr, 0, remain);
    }

    static int ret = Integer.MAX_VALUE;
//    int curCnt; // 统计当前的操作数
    private void process(int[] arr, int cnt, int remain) {
        // 剪枝 当前的次数已经超过已经找到的最小值了
        if (ret < cnt) return;

        // 最终条件，收集结果
        if (remain == 0) ret = Math.min(ret, cnt);

        // 先凑顺子
        for (int i = 0; i <= 5; i++) {
            if (arr[i] > 0 && arr[i + 1] > 0 && arr[i + 2] > 0 && arr[i + 3] > 0 && arr[i + 4] > 0) {
                arr[i] --;
                arr[i + 1] --;
                arr[i + 2] --;
                arr[i + 3] --;
                arr[i + 4] --;
                process(arr, cnt + 1, remain - 5);
                arr[i] ++;
                arr[i + 1] ++;
                arr[i + 2] ++;
                arr[i + 3] ++;
                arr[i + 4] ++;
            }
        }
        
        // 凑对子
        for (int i = 0; i < 10; i++) {
            if (arr[i] >= 2) {
                arr[i] -= 2;
                process(arr, cnt + 1, remain - 2);
                arr[i] += 2;
            }
        }
        
        // 凑单排
        for (int i = 0; i < 10; i++) {
            if (arr[i] >= 1) {
                arr[i] --;
                process(arr, cnt + 1, remain - 1);
                arr[i] --;
            }
        }
    }
}
