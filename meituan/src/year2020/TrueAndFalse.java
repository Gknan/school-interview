package year2020;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/*
给出一个布尔表达式的字符串，比如：true or false and false，表达式只包含true，false，and和or，现在要对这个表达
式进行布尔求值，计算结果为真时输出true、为假时输出false，不合法的表达时输出error（比如：true true）。表达式求
值是注意and 的优先级比 or 要高，比如：true or false and false，等价于 true or (false and false)，计算结果是 true。


输入描述:
输入第一行包含布尔表达式字符串s，s只包含true、false、and、or几个单词（不会出现其它的任何单词），且单词之间用空格分隔。 (1 ≤ |s| ≤ 103).

输出描述:
输出true、false或error，true表示布尔表达式计算为真，false表示布尔表达式计算为假，error表示一个不合法的表达式。

输入例子1:
and

输出例子1:
error

输入例子2:
true and false

输出例子2:
false

输入例子3:
true or false and false

输出例子3:
true
 */
public class TrueAndFalse {

    public static void main(String[] args) {

//        Stack<String> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            Stack<String> stack = new Stack<>();

            String[] s = scanner.nextLine().split(" ");
            if (s.length < 1) {
                System.out.println("error");
                break;
            }
            int i = 0;
            boolean flag = true;
            while (i < s.length) {
//                String next = scanner.next();
                String next = s[i];
                if (next.equals("and")) {
                    // 弹出计算并压入
                    if (i + 1 >= s.length) {
                        System.out.println("error");
                        flag = false;
                        break;
                    }
                    i = i + 1;
                    String afterNext = s[i];
                    if (stack.isEmpty()) {
                        System.out.println("error");
                        flag = false;
                        break;
                    }
                    String beforeNext = stack.pop();
                    String ret = help(beforeNext, next, afterNext);
                    if (ret.equals("invalid")) {
                        System.out.println("error");
                        flag = false;
                        break;
                    }
                    stack.push(ret);
                    i++;
                } else {
                    stack.push(next);
                    i++;
                }
            }

            if (flag == false) continue;
            if (stack.isEmpty()) {
                System.out.println("error");
                break;
            }

            // 弹出栈中计算
            while (stack.size() > 1) {
                String last = stack.pop();
                if (stack.isEmpty()) {
                    System.out.println("error");
                    break;
                }
                String cur = stack.pop();
                if (stack.isEmpty()) {
                    System.out.println("error");
                    break;
                }
                String pre = stack.pop();

                String ret = help(pre, cur, last);
                if (ret.equals("invalid")) {
                    System.out.println("error");
                    break;
                }
                stack.push(ret);
            }

            String ret = stack.pop();
            if (ret.equals("and") || ret.equals("or")) {
                System.out.println("error");
            } else {
                System.out.println(ret);
            }
        }



    }

    private static String help(String beforeNext, String next, String afterNext) {
        if (next.equals("and")) {
            if (beforeNext.equals("or") || beforeNext.equals("and") || afterNext.equals("or") || afterNext.equals("and")) {
                return "invalid";
            }
            if (beforeNext.equals("true") && afterNext.equals("true")) return "true";
            else return "false";
        } else if (next.equals("or")) {
            if (beforeNext.equals("or") || beforeNext.equals("and") || afterNext.equals("or") || afterNext.equals("and")) {
                return "invalid";
            }
            if (beforeNext.equals("true") || afterNext.equals("true")) return "true";
            else return "false";
        }
        return "false";
    }
}
