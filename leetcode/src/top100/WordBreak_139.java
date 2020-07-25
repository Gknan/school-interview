package top100;

import java.util.*;

/*'
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

分析：
递归超时
重复计算，剪枝如 cat sand og cats and og  其中  help(s, 7) 算过一次，就不会算第二次
尝试动态规划分析

宽度优先搜索的思路：
https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode/

 */
public class WordBreak_139 {

    // BFS 思路解决
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }

    public boolean wordBreakDp(String s, List<String> wordDict) {

        if (wordDict.size() < 1) return false;
        if (s == null || s.length() == 0) return false;

        int[] dp = new int[s.length()];
        dp[0] = wordDict.contains(s.substring(0, 1)) ? 1 : -1;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == -1) continue;
                else if (dp[j] == 1) {
                    if (wordDict.contains(s.substring(j + 1, i + 1))) {
                        dp[i] = 1;
                        break;
                    }
                }
            }
            // 前面部分没有能够分割的，从自己分割进行判断
            if (dp[i] == 0) dp[i] = wordDict.contains(s.substring(0, i + 1)) ? 1 : -1;
        }

        return dp[s.length() - 1] == 1 ? true : false;

    }


    int[] visited;

    public boolean wordBreak(String s, List<String> wordDict) {

        if (wordDict.size() < 1) return false;
        if (s == null || s.length() == 0) return false;

        visited = new int[s.length()]; // 0 表示没有计算过，+1 表示返回为true，-1表示返回为false

        return help(s, wordDict, 0);

    }

    private boolean help(String s, List<String> wordDict, int start) {

//        if (wordDict.size() < 1) return false;
//        if (s == null || s.length() == 0) return false;
        if (start >= s.length()) return true;
        if (visited[start] != 0) return visited[start] == 1 ? true : false;

        // 递归尝试
        for (int i = start; i < s.length(); i++) {
            if (!wordDict.contains(s.substring(start, i + 1))) continue;
            // 包含，继续向下
            if (!help(s, wordDict, i + 1)) continue;
            else {
                // 记录 mem
                visited[start] = 1;
                return true;
            }
        }
        visited[start] = -1;
        return false;
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");

        System.out.println(new WordBreak_139().wordBreak(s, wordDict));
        System.out.println(new WordBreak_139().wordBreakDp(s, wordDict));
    }
}
