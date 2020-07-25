package bit;

public class BitTools {

    // 异或特点 相同为 0 不同为 1，计算韩明距离
    public static int hammingDistance(int x, int y) {
        // z 中 1 的个数就是答案
        int z = (x ^ y);

        // 使用每次消去最后一个 1 的方式统计
        int cnt = 0;
        while (z != 0) {
            cnt ++;

            // 消除最后一个 1
            z &= (z - 1);
        }

        return cnt;
    }

    /*
    >>> 无符号右移 高位补 0
    >> 有符号右移
    << 左移
    ~X 取反

    判断奇偶: 0 == (a & 1) 偶数
    交换符号： ~a + 1


     */
}
