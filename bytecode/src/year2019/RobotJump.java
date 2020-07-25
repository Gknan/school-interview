package year2019;

/*
题目描述
机器人正在玩一个古老的基于DOS的游戏。游戏中有N+1座建筑——从0到N编号，从左到右排列。编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位。

起初， 机器人在编号为0的建筑处。每一步，它跳到下一个（右边）建筑。假设机器人在第k个建筑，且它现在的能量值是E, 下一步它将跳到第个k+1建筑。它将会得到或者失去正比于与H(k+1)与E之差的能量。如果 H(k+1) > E 那么机器人就失去 H(k+1) - E 的能量值，否则它将得到 E - H(k+1) 的能量值。

游戏目标是到达第个N建筑，在这个过程中，能量值不能为负数个单位。现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏？
输入
5
3 4 3 2 4
输出
4

假设 取 3
N 3 4 3 2 4
E 3 2 1 0 -4  不正确
备注:
数据约束：
1 <= N <= 10^5
1 <= H(i) <= 10^5

分析：
要求最小的能量值，从E=0 开始向上累加，知道E=x 时满足条件，返回x
满足条件的判断方法，计算过程中的能量不会出现负数，若出现负数，不满足

优化：
上面方法超时
分析后，最终结果一定是小于等于数组H的最大值的

归纳法
倒推法
常用位运算：
判断奇偶： int 型判断 x & 1
求 int a 的第k位 a >> k & 1
int a 第 k 位请0 a=a&~(1<<k)
int a 第 k 位置 1 a= a | (1 << k)
判断是不是2的幂  x&(x-1) == 0
取模运算 a%(2^n)  == a&(2^n-1)
触发 a/(2^n) a>>n
a%2 == a&1
x 的相反数： (~x+1)

位运算应用口诀:
清零取反要用与，某位置一可用或
若要取反和交换，轻轻松松用异或


 */
import java.util.Scanner;

//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//import java.io.IOException;

//import java.util.Arrays;

public class RobotJump {

    // reverse formula calculate
    private static int robotJump3(int[] H) {
        // suppose lastE = 0
        int E = 0;
        int tepSum = 0;
        // reverse cal cause Ek = 2 * Ek-1 + Hk so Ek-1 = Math.ceil((Hk + Ek) / 2)
        for (int i = H.length - 1; i >= 0; i --) {
            tepSum = H[i] + E;
            // judge odd even : x & 1 == 0 even x & 1 ==1 odd
            E = (tepSum & 1) == 0 ? (tepSum >> 1) : (tepSum >> 1) + 1;
        }

        return E;
    }

    // 得到递归公式后 E0 >= ∑2^-i  * Hi
    private static int robotJump2(double[] H) {

        double temp = 0.0;
        for (int i = 0; i < H.length; i ++) {
            temp += H[i] / Math.pow(2, i+1);
        }

        return (int) Math.ceil(temp);

    }

    private static int robotJump(int[] H) {

        int iniE = 0;
        while (true) {
            if (isFinished(iniE, H)) {
                return iniE;
            }
            // update part
            iniE ++;
        }
    }

    private static boolean isFinished(int iniE, int[] H) {
        for (int i = 0; i < H.length; i ++) {
            iniE -= (H[i] - iniE);
            if (iniE < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // for test
        int[] H = {3, 4, 3, 2, 4};
//        int[] H2 = {4, 4, 4};
//        int[] H3 = {1, 6, 4};
//        System.out.println(robotJump(H));
//        System.out.println(robotJump(H2));
//        System.out.println(robotJump(H3));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine());
//        String[] inputHs = br.readLine().split(" ");
//        int[] H = new int[n];
//        for (int i = 0; i < n; i ++) {
//            H[i] = Integer.parseInt(inputHs[i]);
//        }

//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        double[] H = new double[n];
//        for (int i = 0; i < n; i ++) {
//            H[i] = sc.nextDouble();
//        }
////
//        System.out.println(robotJump2(H));
        System.out.println(robotJump3(H));

//        System.out.println(Arrays.toString(H));
    }
}
