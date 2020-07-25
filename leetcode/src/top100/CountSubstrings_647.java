package top100;

import java.util.ArrayList;

/*
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:

输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
示例 2:

输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
注意:

输入的字符串长度不会超过1000。

分析：
1，回溯法，遍历得到所有的情况，每到一种情况，判断是否是回文子串，并统计结果
2，问题落在判断回文子串的处理上，最常规的做法是对于每个子串，首尾两个指针往中间遍历
若到达某一位置不等，返回 false；知道结束，说明不是
3，优化，比如 当前遍历的回文串是 aba 对于后面新家一个元素 x 后得到的新的子串 abax
判断 abax 先可以剪枝，若 x != a，肯定不是回文串，若 x == a,在进行判断回文串 abax

aaa 回溯时 a0a 被当前回文子串来判断，怎么解决子串不连续的问题？

回文串的判断，从左到右和从右到左看是一样的，所以逆序可以判断
长度为 N 的字符串，存在的回文串中心有 2N - 1 个，包括所有的字母和字母之间的空隙，因为回文串有中，奇数和偶数的
另外，如果一个字符串是回文的，那么两边同时减去一个元素依旧是回文的
遍历数组所有中心，对于每个中心，想外扩展，统计回文个数

双指针，控制两个下标，截取字符串，判断字符串是否是回文

DP 算法尝试优化中心扩展法
boolean dp[n][n]
dp[i][j] 是 开始位置是 i，结束位置是 j 的子串，dp[i][j] 的值表示当前位置代表的字符串是否是回文
其中  0<=i<=j<n 所以解空间是上三角
初始值：dp[i][i] 都是 true 表示单元素都是回文的
更新结构：
dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]

马拉车算法，求一个字符串中的最长回文子串的长度，中间扩展发时间复杂度的 O(n2) 他的算法意义在于将时间复杂度降低到 O(N)
主要的思想是以后面字符为中心扩展回文串时，利用前面已经扩展得到的信息

为了屏幕 aa 偶数个数的回文串，我们在每个字母中间添加 # ，将原来的串变成 2N - 1 的长度，这样，处理的字符串个数就是奇数了

Raius[] 数组存放每个字母为中心扩展的回文半径
right：当前得到的最长回文右边界
C：最长回文右边界对应的中心位置
left：最长回文右边界的关于 C 的对称位置
i：当前要判断的回文子串的中心
i': i 关于 C 的对称位置 i' = 2C - i
lefti' ：i' 的回文串的左边界

若 i 在 right 外面：i 按照普通的回文子串求法扩展
若 i 在 right 里面：
    lefti' 在 left 右边（里面），由于对称性，此时 Radius[i] = Radius[i']
    lefti' <= left，i 的左边界等于或者超过了 C 的左边界，那么，Radius[i] 至少为left-i，然后向下扩展下一个位置

处理成 2N - 1 个长度不好算；处理成 2N+1 个长度，最长回文串长度方便计算

马拉车算法解决此题：遍历过程中，通过一个对于i 位置的回文串，因为回文串去掉两端还是回文串
所以回文半径的长度就是i为中心的回文子串的个数，但是由于我们使用了 #，所以需要除以 2

加 # 的目的为了保证每个位置都可以作为中心来扩展，也就是奇数个位置
马拉车算法两个需要注意的边界：R 的下一个位置是最右边界；i > R 说明i超过 R，才开始从此处扩展；
更新 R 和 C 时， 满足 i + rad[i] > R,等于时不更新，更新后的 R= r + rad[i] - 1; 还是保持了R的下一个位置就是最右边

 */
public class CountSubstrings_647 {

    private static int max = Integer.MIN_VALUE;
    private static int manacher(String s) {
        // 为字母之间添加 #
        String ss = manacherStr(s);

//        System.out.println(ss);

        manacherHelp(ss);
        return max;
    }

    private static void manacherHelp(String ss) {
        char[] S = ss.toCharArray();
        int n = S.length;
        int[] rad = new int[n]; // 回文半径数组
        int C = -1;
        int R = -1;

        for (int i = 0; i < n; i ++) {
            if (i >= R) {
                rad[i] = 1;
            } else {
                // ii 就是 i 关于 C 的对称位置
                int ii = 2 * C - i;
                // 涵盖了两种情况，i 的半径至少是两者之间的小值，然后尝试扩展
//                rad[i] = Math.min(rad[ii], R - i);
                rad[i] = Math.min(rad[ii], R - i + 1);
            }

            // 尝试扩展
            while ((i + rad[i]) < n && (i - rad[i]) >= 0 && S[i + rad[i]] == S[i - rad[i]]) {
                // 扩展
                rad[i] ++;
            }

            // 扩展结束了 update C R
            if (R < i + rad[i]) {
//            if (R <= i + rad[i]) {
                R = i + rad[i];
                C = i;
            }

            // 更新 max
//            max = Math.max(max, 2 * rad[i] - 1);
            max = Math.max(max, rad[i]);
        }
        // >>> 无符号右移 移动时高位补0；>> 有符号右移
        // 因为之前有扩展一倍，这里需要除2
//        max >>>= 1;
        max -= 1;

    }

    private static String manacherStr(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i ++) {
            sb.append( "#" + s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }

    private static int cnt = 0;

    // 马拉车算法解决
    public static int countSubstrings5Manacher(String s) {
        if (s == null || s.trim().equals("")) return 0;
        if (s.length() == 1) return 1;

        String ss = manacherStr(s);

        manacherHelp2(ss);
        return cnt;
    }

    private static void manacherHelp2(String ss) {
        char[] S = ss.toCharArray();
        int n = S.length;
        int[] rad = new int[n]; // 回文半径数组
        int C = -1;
        int R = -1;

        for (int i = 0; i < n; i ++) {
//            if (i >= R) {
            if (i > R) {
                rad[i] = 1;
            } else {
                // ii 就是 i 关于 C 的对称位置
                int ii = 2 * C - i;
                // 涵盖了两种情况，i 的半径至少是两者之间的小值，然后尝试扩展
//                rad[i] = Math.min(rad[ii], R - i);
                rad[i] = Math.min(rad[ii], R - i + 1);
            }

            // 尝试扩展
            while ((i + rad[i]) < n && (i - rad[i]) >= 0 && S[i + rad[i]] == S[i - rad[i]]) {
                // 扩展
                rad[i] ++;
            }

            // 扩展结束了 update C R
            if (R < i + rad[i]) {
//            if (R <= i + rad[i]) {
//                R = i + rad[i];
                R = i + rad[i] - 1;
                C = i;
            }

//            System.out.println(rad[i]);
            // 更新 cnt
            cnt += (rad[i]/2);
        }
        // >>> 无符号右移 移动时高位补0；>> 有符号右移
        // 因为之前有扩展一倍，这里需要除2
    }

    // dp 方法
    public static int countSubstrings4(String s) {
        if (s == null || s.trim().equals("")) return 0;
        if (s.length() == 1) return 1;

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // 要先把相邻列的值更新了
        for (int i = 0; i < n; i ++) {
            dp[i][i] = true;
            cnt ++;

            // 要先把相邻列的值更新了
            if (i != n - 1) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    cnt ++;
                }
            }
        }

        for(int gap = 2; gap < n; ++ gap ) {
            for(int i = 0; i < n - gap; ++i)
            {
                int j = i + gap;
                if(dp[i+1][j-1] && s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = true;
                    cnt ++;
                }
            }
        }

        return cnt;
    }

    private static boolean isHuiwen3(String s, int left, int right, boolean[][] dp) {

        // 只有一个
        if (left == right) {
//            cnt ++;
            return true;
        }
        // 两个元素相邻
        if (left + 1 == right) {
            return s.charAt(left) == s.charAt(right);
        }
        return s.charAt(left) == s.charAt(right) && dp[left + 1][right - 1];
    }

    // 双指针，控制两个下标，截取字符串，判断字符串是否是回文 时间时 O(N^3)
    public static int countSubstrings3(String s) {
        if (s == null || s.trim().equals("")) return 0;
        if (s.length() == 1) return 1;

        for (int i = 0; i < s.length(); i ++) {
            for (int j = i; j < s.length(); j ++) {
                // aba case 没有通过，这里的逻辑是不正确的，不能提前剪枝
//                if (isHuiwen2(s, i, j)) {
//                    cnt ++;
//                } else {
//                    break;
//                }
                if (isHuiwen2(s, i, j)) {
                    cnt ++;
                }
            }
        }
        return cnt;
    }

    // 判断 s 的left 到 right 之间的子串是否是回文
    private static boolean isHuiwen2(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left ++;
            right --;
        }
        return true;
    }


    // 中心扩展法
    public static int countSubstrings2(String s) {
        if (s == null || s.trim().equals("")) return 0;
        if (s.length() == 1) return 1;

        for (int i = 0; i < s.length(); i ++) {
            // 统计个数为奇数的回文
            countHuiwen(s, i, i);
            // 统计个数为偶数个
            countHuiwen(s, i, i + 1);
        }

        return cnt;
    }

    private static void countHuiwen(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
            cnt ++;
        }
    }

    // 判断 list 是否是回文字符串
    private static boolean isHuiwen(ArrayList<Character> list) {

        Character[] chs = new Character[list.size()];
        chs = (Character[])list.toArray(chs);
        int i = 0, j = chs.length - 1;
        while (i < j) {
            if (chs[i] != chs[j]) {
                return false;
            }
            // update i j
            i ++;
            j --;
        }

        return true;
    }

    public static void main(String[] args) {
//        Character c1 = 'a';
//        Character c2 = 'a';
//        Character c3 = 'b';
//
//        System.out.println(c1 == c2);
//        System.out.println(c1 == c3);

//        String s = "abc";
        String s = "aaa";
//        int cnt = countSubstrings4(s);


//        System.out.println(cnt);
        System.out.println(countSubstrings5Manacher(s));
//        System.out.println(manacher(s));
    }
}
