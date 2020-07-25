/*
统计原子个数
返回 Map

如 H20 返回 H:2 O:1

思路：
1，从左遍历，都遇到数字，检查前面是括号，对向前找啊到括号里所有元素，个数*2；到达左括号停止
2，若数字前不是括号，前面一个元素的个数*num
时间复杂度，最差情况下，不停的回退，O(N^2) 空间，借助一个栈+map O(N)

借助栈实现

 */

import javafx.scene.Parent;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CountAtom {

    class Pair {
        String ch;
        int cnt;

        public Pair(String ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }

    public Map<String, Integer> getResult(String str) {
        if (str == null || str.length() < 1) return new HashMap<>();

        HashMap<String, Integer> map = new HashMap<>();

        Stack<Pair> stack = new Stack<>();

        Stack<Pair> help = new Stack<>();

        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= 'A' && chs[i] <= 'Z') {
//                if (i + 1 < chs.length && chs[i + 1] >= 'a' && chs[i + 1] <= 'z') {
//                    map.put(chs[i] +""+ chs[i + 1], map.getOrDefault(chs[i] +""+ chs[i + 1], 0) + 1);
//                    i ++;
//                    continue;
//                }
//                map.put(chs[i] +"", map.getOrDefault(chs[i] +"", 0) + 1);
                stack.push(new Pair(String.valueOf(chs[i]), 1));
            } else if (chs[i] >= 'a' && chs[i] <= 'z') {
                Pair top = stack.pop();
                top.ch += chs[i];
                stack.push(top);
            } else if (chs[i] == '(') {
                stack.push(new Pair(String.valueOf(chs[i]), 1));
            } else if (chs[i] == ')') {
                stack.push(new Pair(String.valueOf(chs[i]), 1));
            } else if (chs[i] >= '1' && chs[i] <= '9') {
                // 可能是二位数吗？
                // 假设都是一位数 若前面是有括号，括号内所有元素的个数*cns[i] 若没有右括号，只弹出一个修改
                Pair top = stack.pop();
                if (!")".equals(top.ch)) {
//                    top = stack.pop();
                    top.cnt *= Integer.parseInt(String.valueOf(chs[i]));
                    // 压回去
                    stack.push(top);
                } else {
                    // 找到右括号，删除之前的左括号
//                    stack.pop();
                    // 弹出括号前
                    while (!stack.isEmpty()) {
                        if (stack.peek().ch.equals("(")) break;
                        Pair cur = stack.pop();
                        cur.cnt *= Integer.parseInt(String.valueOf(chs[i]));
                        help.push(cur);
                    }

                    // 删除匹配的左括号
                    stack.pop();

                    while(!help.isEmpty()) {
                        stack.push(help.pop());
                    }
                }
            }
        }

        // 弹出栈，更新 map
        while (!stack.isEmpty()) {
            Pair cur = stack.pop();
            if (cur.ch.equals("(") || cur.ch.equals(")")) continue;
            map.put(cur.ch, map.getOrDefault(cur.ch, 0) + cur.cnt);
        }

        return map;
    }

    public static void main(String[] args) {
        CountAtom test = new CountAtom();

        String string = "HMg2(H2O(N3Ag)2)3N2";

        System.out.println(test.getResult(string));
    }

}
