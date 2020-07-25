package top100;

import java.util.*;

/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
映射关系就是九键拼音的关系
2 abc
3 def
4 ghi
5 jkl
6 mno
7 pqrs
8 tuv
9 wxyz

示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

分析：
建立每个数字的映射关系表
遍历每个数字，遍历每个数字对应的映射关系，组合成结果
其实类似于笛卡尔集

注意区分回溯算法框架和DFS 算法框架 DFS 的起始点只有一个；而回溯的起始点可以由若干个

时间复杂度 ： O(3^N * 4^M) N 是映射三个字母的数字的个数，M 是映射四个字母的数字的个数
空间复杂度：O(3^N * 4^M)

优化：
剪枝可否?不能，每个位置都是有用的
map 可以不用，定义计算规则，直接使用 ASCII 码计算



 */
public class LetterCombinations_17 {
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.trim().length() == 0) return new ArrayList<>();

        HashMap<Integer, List<Character>> map = new HashMap<>();

        map.put(2, Arrays.asList('a', 'b', 'c'));
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));
        map.put(5, Arrays.asList('j', 'k', 'l'));
        map.put(6, Arrays.asList('m', 'n', 'o'));
        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        map.put(8, Arrays.asList('t', 'u', 'v'));
        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));

        List<String> ret = new ArrayList<>();
        LinkedList<Character> list = new LinkedList<>();
        char[] chs = digits.toCharArray();
//        System.out.println(map);
//        process(chs, 0, map, ret, list);

        dfs(chs, 0, map, ret, list);

        return ret;
    }

    private static void dfs(char[] chs, int i, HashMap<Integer,
            List<Character>> map, List<String> ret, LinkedList<Character> list) {

        if (i == chs.length) {
            // 收集结果
            StringBuilder sb = new StringBuilder();
            for (char c: list) {
                sb.append(c);
            }
            ret.add(sb.toString());

            return;
        }

        List<Character> tempList = map.get(Integer.parseInt(chs[i] + ""));
        for (char c: tempList) {
            list.add(c);
            dfs(chs, i + 1, map, ret, list);
            list.removeLast();
        }
    }

//    private static void process(char[] chs, int idx, HashMap<Integer,
//            List<Character>> map, List<String> ret, LinkedList<Character> list) {
//        if (idx > chs.length) return;
//        if (idx == chs.length) {
//            StringBuilder sb = new StringBuilder();
//            for (char c: list) {
//                sb.append(c);
//            }
//            ret.add(sb.toString());
//
//            return;
//        }
//
//        for (int i = idx; i < chs.length; i ++) {
//            List<Character> tempList = map.get(Integer.parseInt(chs[i] + ""));
//            for (char c: tempList) {
//                list.add(c);
//                process(chs, i + 1, map, ret, list);
//                list.removeLast();
//            }
//        }
//    }

    public static void main(String[] args) {

        String digits = "23";

        System.out.println(letterCombinations(digits));
    }
}
