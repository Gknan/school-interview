package top100;

import java.util.HashMap;

/*
有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的
方式使这两个字符串相同。
每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。

示例 1：
输入：s1 = "xx", s2 = "yy"
输出：1
解释：
交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
示例 2：

输入：s1 = "xy", s2 = "yx"
输出：2
解释：
交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
示例 3：

输入：s1 = "xx", s2 = "xy"
输出：-1
示例 4：

输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
输出：4
 

提示：

1 <= s1.length, s2.length <= 1000
s1, s2 只包含 'x' 或 'y'。

base case : s1= xx s2 = yy ; s1 = xy, s2 = yx
对于更长的 s1 s2：先确定不变的位置，即下标相等且位置上相等；整理剩下的上下不等的部分
按照 第一行的 x y 排序，排序后两两相消除；
优化；这里使用 map 保存 剩下的信息，key 为 x_y y_x 两种，value 是对应的出现的次数；x_y 标识的是对应位置上面是 x ，下面是 y


 */
public class MinimumSwap_1247 {
    public int minimumSwap(String s1, String s2) {

        if (s1 == null || s2 == null || s1.length() != s2.length()) return 0;// 定义异常策略

        HashMap<String, Integer> map = new HashMap<>();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();

        for (int i = 0; i < chs1.length; i ++) {
//            if ((chs1[i] != 'x' && chs1[i] != 'y') || (chs2[i] != 'x' && chs2[i] != 'y'))
//                throw new IllegalArgumentException("s1 or s2 is not legal.");

            String key = chs1[i] + "_" + chs2[i];
            if (chs1[i] != chs2[i]) map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int ret = 0;
        int xy = map.getOrDefault("x_y", 0);
        int yx = map.getOrDefault("y_x", 0);

        // xy 和 xy 消除，yx 和yx 两两消除
        int xyCnt = xy / 2;
        int yxCnt = yx / 2;

        ret += xyCnt + yxCnt;

        xy %= 2;
        yx %= 2;

        if (xy > 0 && yx > 0) ret +=2;
        else if (xy != yx) ret = -1;

        return ret;
    }
}
