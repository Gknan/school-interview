package backtracking;

import java.util.*;

/*
给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回一个空列表。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: []

解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。

由于是求最短路径，所以，我们使用 BFS 更合理；但是这里返回值要返回解的序列；
多个解的话，BFS 怎么记录解的状态 借助  Map<String, List<String>> 可以找到解记录，保存所有点之间的关系；最后可以整理出来结果

一层一层遍历；之前的 queue 记录的是下一层的的所有节点；为了得到结果
这里的 queue 需要记录的是 整条链路的信息，如 a - ab -aba 三层，那就要记录 三层的列表 Queue<LinkedList<String>>
visited 记录已经访问过的层数出现过的 单词 ，因为 更高的层次扩展的结果已经包含了最低的 HashSet 记录
flag 记录是否达到结果状态  若某一层找到 endword，那么需要遍历玩这一层后才能汇总结果
每个单词的替换方式，拆成字符数组，然后替换掉数组中每个元素，查询是否在字典中，并且在之前的层没有访问过
由于 经常查 dict 所以讲 list 状变为 set

 */

public class FindLadders_126 {

    List<List<String>> retList;
    public List<List<String>> findLadders(String beginWord,
                                          String endWord, List<String> wordList) {

        retList = new ArrayList<>();
//        if (wordList.size() < 1) return retList;

        // wordList 转为 set
        HashSet<String> dict = new HashSet<>(wordList);

        // 不包含，一定不存在路径
        if (!dict.contains(endWord)) return retList;

        // 定义 queue
        Queue<ArrayList<String>> queue = new LinkedList<>();

        // 定义 k - 1 层之前已经访问过的字符串
        HashSet<String> visited = new HashSet<>();

//        Queue<String> queue = new LinkedList<>();
        // startWord 加入到以访问，queue中
        ArrayList<String> first = new ArrayList<>();
        first.add(beginWord);
        queue.offer(first);

        boolean flag = false; // 访问结束标记

        while (!queue.isEmpty() && !flag) {

            // 一次遍历一层
            int num = queue.size();

            HashSet<String> subVisited = new HashSet<>();
            for (int i = 0; i < num; i++) { // 当前层的每一个元素扩展
                // 取出每个列表的最后一个单词，就是当前层的单词
                // curList 的最后一个单词就是当前的单词，若
                ArrayList<String> curList = ((LinkedList<ArrayList<String>>) queue).removeFirst();
                String cur = curList.get(curList.size() - 1); // 得到当前层的单词
                char[] chars = cur.toCharArray(); // 转为字符数组，方便操作
                for (int j = 0; j < chars.length; j++) {
                    char old = chars[j]; // 原来字符中的第 j 个位置 记录
                    for (char c = 'a'; c <= 'z'; c ++) { // 待换的字符 从 a 到 z

                        if (c == old) continue; // 若已经试过

                        chars[j] = c; // 第 j 个位置换成 c 字符
                        String newStr = new String(chars);
                        if (!visited.contains(newStr) && dict.contains(newStr)) {// 若当前更新后的字符没有访问过且在字典中，添加到下一层
                            if (newStr.equals(endWord)) {
                                flag = true;
                            }
                            // 收集结果
                            ArrayList curNodes = new ArrayList(curList);
//                            curList.add(newStr);// 新扩展出的节点添加到列表中，加入到 queue中
                            curNodes.add(newStr);
                            queue.offer(curNodes);

                            subVisited.add(newStr); // 统计这一层的放过过的节点
                            // 设置访问标记
//                            visited.add(newStr); // 这里就更新访问标志，会导致会面同层的序列用到时跳过，建议遍历玩一层后在汇总更新 visited
                        }
                    }
                    // 还原信息后 更改下一位
                    chars[j] = old;
                }
            }
            // 更细玩一层，更新 visited
            visited.addAll(subVisited);
//            // 找到解， 结果循环
//            if (flag) break;
        }

        // 收集结果 queue 中的数据结果是 ArrayList 是从根到结果的层数中所有信息
        // 遍历 queue 收集答案
        for (ArrayList<String> curList: queue) {
            if (curList.get(curList.size() - 1).equals(endWord)) {
                retList.add(curList);
            }
        }

        return retList;
    }

    public static void main(String[] args) {
        FindLadders_126 test = new FindLadders_126();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList(
                "hot","dot","dog","lot","log","cog"
        );

        System.out.println(test.findLadders(beginWord, endWord, wordList));
    }
}
