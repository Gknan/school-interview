package top100;

/*
两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 231.

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)

分析：
1 位运算计算题
2 首先，两个数异或运算，得到数字中 1 的个数就是结果；由于 x y 都是正整数，所以不存在负数的情况
4 统计 z 中 1 的个数，& 0x01 时间复杂度是 O(K) 若
5  z & (z - 1) 消去最后一个0，按照这种方式消除，时间复杂度降到 O(M) M 是 z 中 1 的个数
 */
public class HammingDistance_461 {

    public static int hammingDistance(int x, int y) {
        //1
        int z = (x ^ y);

        //
        int cnt = 0;
        while (z != 0) {
            cnt ++;
            z &= (z - 1);
        }

        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }
}
