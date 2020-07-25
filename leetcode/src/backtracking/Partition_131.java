package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]

回溯方案怎么写：
 s 字符串截取方案，
 最终每个截取方案中，实际上给的是整个子串的一个分割
 每次分割，注意的是 s ，第一道有 s.length() - 1 个位置去切割


 */
public class Partition_131 {

    // 代码改写
    List<List<String>> retList;
    public List<List<String>> partition(String s) {
        // 初始化结果表
        retList = new ArrayList<>();

        if (s.length() < 1) return retList;

        LinkedList<String> path = new LinkedList<>();

        backtrack(s, 0, path);
        return retList;
    }

    /**
     *
     * @param s 字符串
     * @param start 当前匹配到的 位置 其实相当于截取字符串是子串的第一个字符在最开始串的的位置
     * @param path 已经选过的路径
     */
    private void backtrack(String s, int start, LinkedList<String> path) {
        if (start == s.length()) {
            // 收集答案
            retList.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // 划分
            // 这里截取字符串容易出错，使用 索引判定后再决定
            if (isParliment(s, start, i)) { // start ~ i 相当于是截取到的部分
                String part1 = s.substring(start, i + 1);
                path.add(part1);
                backtrack(s, i + 1, path);
                path.removeLast();
            }
        }
    }

    /**
     * 两边指针向中间法判定是否是回文
     * @param s
     * @param left 左边开始的索引
     * @param right 右边开始的索引
     * @return
     */
    private boolean isParliment(String s, int left, int right) {

        char[] chars = s.toCharArray();
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }

            left ++;
            right --;
        }
        return true;
    }


//    List<List<String>> retList;
//    public List<List<String>> partition(String s) {
//        // 初始化结果表
//        retList = new ArrayList<>();
//
//        if (s.length() < 1) return retList;
////        if (s.length() < 2) {
////            retList.add(Arrays.asList(s));
////        }
//
//        LinkedList<String> path = new LinkedList<>();
//        for (int i = 1; i <= s.length(); i++) {
//            // 切割一刀，分成两部分，part1 part2 part1 是回文，加入到 path 记录中，然后去 匹配 part2 部分
//            String part1 = s.substring(0, i);
//            if (isHuiwen(part1)) {
//                String part2 = s.substring(i);
//                path.add(part1);
//                help(part2, path);
//                // 还原现场
//                path.removeLast();
//
//            }
//        }
//
//        return retList;
//    }

    private void help(String str, LinkedList<String> path) {
//        if (str.isEmpty()) {
        if (str.length() == 0) { // 只剩下一个元素，一定是回文的，添加到path 中，并收集结果
            // 终止 收集结果
//            path.add(str);
            retList.add(new ArrayList<>(path));

            // 还原线程
//            path.removeLast();

            // 结束返回
            return;
        }

        // 没有终止，划分 str 划分的方式同 主方法中
        for (int i = 1; i <= str.length(); i++) {
            // 切割一刀，分成两部分，part1 part2 part1 是回文，加入到 path 记录中，然后去 匹配 part2 部分
            String part1 = str.substring(0, i);
            if (isHuiwen(part1)) {
                String part2 = str.substring(i);
                path.add(part1);
                help(part2, path);
                path.removeLast();
            }
        }
    }

    // 中心扩散法 判断是否是回文
    private boolean isHuiwen(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;

        while (i < j) {
            if (chars[i] != chars[j]) return false;

            i ++;
            j --;
        }

        return true;
    }

    public static void main(String[] args) {
        Partition_131 test = new Partition_131();


        String s = "aab";
        System.out.println(test.partition(s));
//        System.out.println(s.substring(0));
//        System.out.println(s.substring(1));
//        System.out.println(s.substring(2));
//        System.out.println(s.substring(3));
    }
}
