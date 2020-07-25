package top100;

import java.util.ArrayDeque;
import java.util.LinkedList;

/*
给定一个经过编码的字符串，返回它解码后的字符串。
编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例:

s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

借助栈实现括号匹配
每次匹配到 ] 时，使用另外一个栈保留出栈的字母，拼接成字符串后入栈

ArrayDeque 直接使用 AddFirst AddLast RemoveFirst RemoveLast 这样 API 更方便

问题点：前面的数字可能不止是个位数，还有可能是 10 100 位数

辅助栈：
遇到 数字，multi = muilt * 10 + 数字
遇到 [ ，str ，nulti 入栈
遇到 字母， 数字和字母 str += 字母，
遇到 ] lastStr + muitl * str

 */
public class DecodeString_394 {

    // 递归法
    public static String decodeString3(String s) {

        return help(s, 0)[1];
    }

    /**
     * 求s[idx..] 对应的解码结果
     * @param s
     * @param idx
     * @return ret[0] 下标 ret[1] 结果
     */
    private static String[] help(String s, int idx) {

        StringBuilder ret = new StringBuilder();
        int multi = 0;

        int i = idx;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                // 左括号，进入下一个递归
                String[] nextRet = help(s, i + 1);
                // 更新 i
                i = Integer.parseInt(nextRet[0]);
                // 结果添加到 ret
                for (int j = 0; j < multi; j++) {
                    ret.append(nextRet[1]);
                }
                // multi 使用过了，需要重置
                multi = 0;
            } else if (c ==']') {
                // 返回跳出递归
                return new String[]{String.valueOf(i), ret.toString()};
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Character.digit(c, 10);
            } else {
                ret.append(c);
            }
        }

        return new String[]{String.valueOf(i), ret.toString()};
    }

    public static String decodeString2(String s) {

        // s.length = 1 ，返回 s
        if (s == null || s.length() < 2) return s;
        LinkedList<String> chStack = new LinkedList<>();
        LinkedList<Integer> numStack = new LinkedList<>();

        StringBuilder temp = new StringBuilder();
        int multi = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                // 入栈
                numStack.addLast(multi);
                chStack.addLast(temp.toString());

                // 重置
                multi = 0;
                temp = new StringBuilder();
            } else if (c == ']') {
                // 收集结果
                String lastStr = chStack.pollLast();
                Integer num = numStack.pollLast();
                for (int j = 0; j < num; j++) {
                    lastStr += temp.toString();
                }
                // 拼接并再添加进 stack
//                chStack.push(lastStr);
                temp = new StringBuilder(lastStr);
            } else if (c >= '0' && c <= '9') {
                // 当前为数字
                multi = multi * 10 + Character.digit(c, 10);
            } else {
                // 当前为 字母
                temp.append(c);
            }
        }


        return temp.toString();
    }

    public static String decodeString(String s) {

        if (s == null || s.length() < 2) return "";

        ArrayDeque<String> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ']') {
                stack.push(String.valueOf(chars[i]));
            } else {
                ArrayDeque<String> temp = new ArrayDeque<>();
                while (!stack.isEmpty()) {
                    String poll = stack.poll();
                    if (!poll.equals("[")) {
                        temp.push(poll);
                        continue;
                    } else {
                        break;
                    }
                }

                LinkedList<Integer> num = new LinkedList<>();
                while (!stack.isEmpty() && (stack.peekLast().toCharArray()[0] >= '0' && stack.peekLast().toCharArray()[0] <= '9')) {
                    String poll = stack.removeLast();
                    Integer cur = Integer.valueOf(poll);
                    num.addLast(cur);
                }

                // 统计 num
                int cnt = 0;
                for (int j = 0; j < num.size(); j++) {
                    cnt = cnt * 10 + num.get(j);
                }


                // 收集结果填充进 stack
                StringBuilder sb = new StringBuilder();
                while (!temp.isEmpty()) {
                    sb.append(temp.poll());
                }
                // 添加多个
                for (int j = 0; j < cnt; j++) {
                    stack.push(sb.toString());
                }
            }
        }

        // 遍历取出结果
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pollLast());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";

        System.out.println(decodeString2(s));
        System.out.println(decodeString3(s));
    }
}
