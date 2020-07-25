package backtracking;

/*
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false

分析：

用 pattern 去 匹配 s


 */
public class IsMatch10 {

//    // 自底向上 填充 DP 表
//    public boolean isMatch3(String s, String p) {
//
//    }


    int[][] mem; // 0 表示没有匹配，1 表示匹配成功，-1表示匹配失败
    // 加备忘录 mem[i][j] 表示 s[i:] p[j:] 是否匹配
    public boolean isMatch2(String s, String p) {
        // 注意数组的刹那长度，这里使用
        mem = new int[s.length() + 1][p.length() + 1];

        return process(s, p, 0, 0);
    }

    private boolean process(String s, String p, int sI, int pI) {
        // 若当前位置异步已经算过，直接返回
        if (mem[sI][pI] != 0) return mem[sI][pI] == 1 ? true: false;

        // 若 pattern 匹配完成， s 还没有完，则匹配失败，若此时 s 也完了，匹配成功
        if (pI == p.length()) {
            boolean f  = sI == s.length();
            if (f) {
                mem[sI][pI] = 1;
            } else
                mem[sI][pI] = -1;

            return mem[sI][pI] == 1 ? true: false;
        }

        // 查看第一个字符是否匹配 p 的 第一个字符属于 [s[0] ‘.’] 时，是匹配的
        // 到这里， s 可能是空， p 不一定是空；所以 ，使用 s.charAt 需要进行判断
//        boolean firstMatch = s.length() >= 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        boolean firstMatch = (sI <= s.length() - 1) && (p.charAt(pI) == s.charAt(sI) || p.charAt(pI) == '.');

        // 若 p 有 * 通配符的处理 通配符一定是在 第二位，不可能是在第一位
        if (pI <= p.length() - 2 && p.charAt(pI + 1) == '*') {
            // 匹配
            // 按照 统配到 0 个来看，直接去掉 p 中 第一个第二个字符
            boolean flag1 = process(s, p, sI, pI + 2);
            // 根据 firstMatch 的情况，若第一个匹配成功，则 将 s 的减少一位，保留 p，就相当于移动了 s 来反映 匹配的结果；
            // 同时，由于我们保留了 p，就是保留了通配符，所以统配符能保留的信息还是保留着
            boolean flag2 = firstMatch && process(s, p, sI + 1, pI);

            boolean f  = flag1 || flag2;
            if (f) {
                mem[sI][pI] = 1;
            } else
                mem[sI][pI] = -1;

            return mem[sI][pI] == 1 ? true: false;
        } else {
            boolean f  = firstMatch && process(s, p, sI + 1, pI + 1);
            if (f) {
                mem[sI][pI] = 1;
            } else
                mem[sI][pI] = -1;

            return mem[sI][pI] == 1 ? true: false;
        }
    }

    // 该函数 返回的是当前 s 和 p 是否匹配
    // 递归的思路 往下做
    public boolean isMatch(String s, String p) {

//        if (s.isEmpty()) return p.isEmpty();
        // 若 pattern 匹配完成， s 还没有完，则匹配失败，若此时 s 也完了，匹配成功
        if (p.isEmpty()) return s.isEmpty();

        // 查看第一个字符是否匹配 p 的 第一个字符属于 [s[0] ‘.’] 时，是匹配的
        // 到这里， s 可能是空， p 不一定是空；所以 ，使用 s.charAt 需要进行判断
        boolean firstMatch = s.length() >= 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        // 若 p 有 * 通配符的处理 通配符一定是在 第二位，不可能是在第一位
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 匹配
            // 按照 统配到 0 个来看，直接去掉 p 中 第一个第二个字符
            boolean flag1 = isMatch(s, p.substring(2));
            // 根据 firstMatch 的情况，若第一个匹配成功，则 将 s 的减少一位，保留 p，就相当于移动了 s 来反映 匹配的结果；
            // 同时，由于我们保留了 p，就是保留了通配符，所以统配符能保留的信息还是保留着
            boolean flag2 = firstMatch && isMatch(s.substring(1), p);

            return flag1 || flag2;
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    // target 的 tIDx 开始，和 pattern 的 pIdx 是否匹配
//    private boolean backtrack(String target, String pattern, int tIdx, int pIdx) {
//        if (pIdx == pattern.length()) return tIdx == target.length();
//        if (tIdx == target.length()) return pIdx == pattern.length();
//
//        if (pattern.charAt(pIdx) != '?' && pattern.charAt(pIdx) != '*') {
//            return pattern.charAt(pIdx)  == target.charAt(tIdx)  && backtrack(target, pattern, ++ tIdx, ++ pIdx);
//        }
//        if (pattern.charAt(pIdx) == '?') {
//            return backtrack(target, pattern, ++ tIdx, ++ pIdx);
//        }
//        if (pattern.charAt(pIdx) == '*') {
//            boolean flag = false;
//            for (int i = 0; i < target.length() - pIdx; i++) {
////                String newPattern = build(i, pattern.charAt(pIdx - 1)) +  pattern; 怎么取消patern 中的 * 号
//                String newPattern = pattern.substring(0, pIdx) +
//                        build(i, pattern.charAt(pIdx - 1))  + pattern.substring(pIdx + 1);
//                flag |= backtrack(target, newPattern, tIdx, pIdx + i);
//            }
//
//            return flag;
//        }
//
//        return false;
//    }

    // 常见 * 字符串
    private String build(int i, char c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s= "aa";
        String p = "a*";
        System.out.println(new IsMatch10().isMatch(s, p));
//        System.out.println("".isEmpty());
    }
}
