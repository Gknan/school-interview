package huiwen;

import java.util.ArrayList;

public class CountOfSubstrings {

    private static int max = Integer.MIN_VALUE;

    // 马拉车算法解决最长回文半径
    private static int manacher(String s) {
        // 为字母之间添加 #
        String ss = manacherStr(s);
        manacherHelp(ss);
        return max;
    }

    // manacher for 最长回文半径
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
            max = Math.max(max, rad[i]);
        }
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

    // 马拉车算法 求 回文子串个数
    public static int countSubstrings5Manacher(String s) {
        if (s == null || s.trim().equals("")) return 0;
        if (s.length() == 1) return 1;

        String ss = manacherStr(s);

        manacherHelp2(ss);
        return cnt;
    }

    // 原始
    private static void manacherHelp2(String ss) {
        char[] S = ss.toCharArray();
        int n = S.length;
        int[] rad = new int[n]; // 回文半径数组
        int C = -1;
        int R = -1;

        for (int i = 0; i < n; i ++) {
            if (i > R) {
                rad[i] = 1;
            } else {
                // ii 就是 i 关于 C 的对称位置
                int ii = 2 * C - i;
                // 涵盖了两种情况，i 的半径至少是两者之间的小值，然后尝试扩展
                rad[i] = Math.min(rad[ii], R - i + 1);
            }

            // 尝试扩展
            while ((i + rad[i]) < n && (i - rad[i]) >= 0 && S[i + rad[i]] == S[i - rad[i]]) {
                // 扩展
                rad[i] ++;
            }

            // 扩展结束了 update C R
            if (R < i + rad[i]) {
                R = i + rad[i] - 1;
                C = i;
            }

            // 更新 cnt
            cnt += (rad[i]/2);
        }
    }

    // dp 方法
    public static int countSubstrings4(String s) {
        if (s == null || s.trim().equals("")) return 0;
        if (s.length() == 1) return 1;

        int n = s.length();
        // dp[i][j] 表示 s[i,..,j] 是否是回文串
        boolean[][] dp = new boolean[n][n];

        // 填表发现这个更新是按照对角线走的
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
                if (isHuiwen2(s, i, j)) { // 判断 i 到 j 之间的串是否是回文
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
