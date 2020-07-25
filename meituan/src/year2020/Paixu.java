package year2020;

import com.sun.applet2.AppletParameters;

import java.util.*;

/*
已知一种新的火星文的单词由英文字母（仅小写字母）组成，但是此火星文中的字母先后顺序未知。给出一组非空的火星文单词，且此组单词已经按火星文字典序进行好了排序（从小到大），请推断出此火星文中的字母先后顺序。


输入描述:
一行文本，为一组按火星文字典序排序好的单词(单词两端无引号)，单词之间通过空格隔开


输出描述:
按火星文字母顺序输出出现过的字母，字母之间无其他字符，如果无法确定顺序或者无合理的字母排序可能，请输出"invalid"(无需引号)




输入例子1:
z x

输出例子1:
zx

输入例子2:
wrt wrf er ett rftt

输出例子2:
wertf

输入例子3:
z x z

输出例子3:
invalid

根据给的字符串构建图；拓扑排序，得到结果
若有环，则返回 invalid

用什么结构来保存图的信息；
矩阵，链表
记录入度信息 indegre[] 每个节点的入度

1 根据字符串序列得到 点一点之间的关系

 */
public class Paixu {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int[] indrees = new int[26];
        HashMap<Character, Integer> indrees = new HashMap();
//        Arrays.fill(indrees, -1);
//        for (int i = 0; i < 26; i++) {
//            indrees[i] = -1;
//        }
        HashMap<Character, ArrayList<Character>> map = new HashMap<>();
        // 先把空的添加进去

//        String[] s = scanner.nextLine().split(" ");

        String string = "wrt wrf er ett rftt";
        String[] s = string.split(" ");

//        System.out.println(Arrays.toString(s));

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                set.add(s[i].charAt(j));
            }
        }
//        int[][] prerequisites = convert(set, s);

        String fisrtChStr = "";
        for (int i = 0; i < s.length; i++) {
//            update(s[i], indrees);
            updateEdge(s[i], map);
            fisrtChStr += s[i].charAt(0);
        }

        updateEdge(fisrtChStr, map);

        for (char ch : set) {
            indrees.put(ch, 0);
            if (!map.containsKey(ch)) {
                map.put(ch, new ArrayList<>());
            }
        }

        // update indrees use eadges infos
        for (char key : map.keySet()) {
            for (char value : map.get(key)) {
                // value 对应的入度 + 1
//                indrees[value - 'a'] = indrees[value - 'a'] == -1 ? 1 : indrees[value - 'a'] + 1;
                indrees.put(value, indrees.get(value) + 1);
            }
        }

        // 每次删除一个 入度为 0 的节点，并删除和领结的边
        int n = set.size(); // 总共搜索 节点个数次

        // 应该使用的是宽度有限
        Queue<Character> queue = new LinkedList<>();
        for (char ch : indrees.keySet()) {
            if (indrees.get(ch) == 0) {
                queue.offer(ch);
            }
        }

        ArrayList<Character> retList = new ArrayList<>();
//        for (int i = 0; i < map.size(); i++) {
        // 从栈中拿出不为0 de
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            retList.add(cur);
            // 删除该节点，修改对应 indrees
            n--;
            // 修改对应的下一个节点的入度
            for (char next : map.get(cur)) {
                indrees.put(next, indrees.get(next) - 1);
                if (indrees.get(next) == 0) {
                    // 产生了新的入度为 0 的点，加入到 queue 中
                    queue.offer(next);
                }
            }
        }


        boolean flag = n == 0;
        if (flag) {
            StringBuilder sb = new StringBuilder();
            for (char c : retList) {
                sb.append(c);
            }

            System.out.println(sb.toString());
        } else {
            System.out.println("invalid");
        }
    }

    private static void updateEdge(String s, HashMap<Character, ArrayList<Character>> map) {

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] != chars[i + 1]) {
                ArrayList<Character> curList = map.getOrDefault(chars[i], new ArrayList<Character>());
                if (curList.contains(chars[i + 1])) continue;
                curList.add(chars[i + 1]);
                map.put(chars[i], curList);
            }
        }
    }
}
