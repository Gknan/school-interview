package year2020;


/*
给出两个字符串，分别是模式串P和目标串T，判断模式串和目标串是否匹配，匹配输出 1，不匹配输出 0。模式串中‘？’可以匹配目标
串中的任何字符，模式串中的 ’*’可以匹配目标串中的任何长度的串，模式串的其它字符必须和目标串的字符匹配。例如P=a?b，T=acb，
则P 和 T 匹配。


输入描述:
输入第一行包含一个字符串p， (1 ≤ |p| ≤ 20).

输入第二行包含一个字符串t， (1 ≤ |t| ≤ 20).


输出描述:
输出仅包含0和1的整数，0表示p和t不匹配，1表示p和t匹配。


输入例子1:
a?b
ab

输出例子1:
0

输入例子2:
a*b
ab

输出例子2:
1

输入例子3:
a*b
a(cb

输出例子3:
1


123?*223??*
12354732235590pi
 */

import java.util.Scanner;

public class PatternMatch {

    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//
//        String pattern = s.nextLine();
//        String target = s.nextLine();

        String pattern = "123?*223??*";
        String target = "1235  2235590pi";

        boolean ret = match(pattern, target, 0, 0);
        System.out.println(ret == true ? "1" : "0");
    }

    private static boolean match(String pattern, String target, int pI, int tI) {

        if (tI == target.length()) return pI == pattern.length();
        if (pI == pattern.length()) return tI == target.length();


        if (pattern.charAt(pI) != '?' && pattern.charAt(pI) != '*') {
            return pattern.charAt(pI) == target.charAt(tI) && match(pattern, target, ++pI, ++tI);
        }

        if (pattern.charAt(pI) == '?') {
            return match(pattern, target, ++pI, ++tI);
        }

        if (pattern.charAt(pI) == '*') {// 多个 * 的处理
            // 若当前的 pattrn 中的 * 是最后一个，则直接返回 true
            if (pI == pattern.length() - 1) return true;
            boolean b3 = false;
            for (int i = tI; i < target.length(); i++) {
                b3 |= match(pattern, target, pI + 1, i);
            }

            return b3;
        }

        return false;
    }


}
