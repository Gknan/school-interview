package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

示例 1:

输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1
示例 2:

输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。

分析：
n = 2 时， 0 1 2 3 不是结果 因为 01 和 10 有两位不同
所以不能直接从小到大生成；
因为两个相邻的编码一定只相差一个位置不一样，考虑根据上一个位置生成本位置
又因为全局不能重入，所以使用 boolean 数组保存已经保存过的编码


 */
public class GrayCode_89 {

    public List<Integer> grayCode2(int n) {
        List<Integer> ret = new ArrayList<>();
        
        ret.add(0);

        int mask = 1;
        for (int i = 0; i < n; i++) {
            // 当前的队列中元素拿出来，添加元素并镜像
            for (int j = ret.size() - 1; j >= 0 ; j--) {
                ret.add(mask + ret.get(j));
            }
            // 更新 mask
            mask <<= 1;
        }

        return ret;

//        boolean [] visited = new boolean[(int)Math.pow(2, n)];
//
//        int start = 0;
////        String string = Integer.toBinaryString(start);
//
//        // 先把 0 添加进去
//        ret.add(0);
//        int mask = 1;
//        while (mask <= (1 << (n - 1))) {
//            start |= mask;
//            ret.add(start);
//
//            // update mask
//            mask <<= 1;
//        }
//        // 从高位移动 每次出去最后一个 1
//        while ((start & start - 1) != 0) {
//            start &= start - 1;
//            ret.add(start);
//        }

//        return ret;
    }
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();

        if (n < 0) {
//            ret.add(0);
            return ret;
        }

        for (int i = 0; i < Math.pow(2, n); i++) {
            ret.add(i);
        }

        return ret;
    }

    public static void main(String[] args) {
        GrayCode_89 test = new GrayCode_89();
        System.out.println(test.grayCode2(4));
    }
}
