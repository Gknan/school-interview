package year2019;

/*
题目：
按要求编写皮配字符串的代码
1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC

思路：
i i1 i2 i3 分别只想四个连续的字母
遍历字符串，若i1 与i不相等，i向下移动一个位置；
若i1和i相等，判断i2是否与i1相等，若相等，继续向下找，找到第一个不等的位置，删除i1和i2之间的字符；
若i2和i1不等，判断i3是否等于i2，若等于，找到i3之后第一个不等于i2的位置，删除i2和i3之间的字符
i 指向 i3的位置，重新开始

注意：
过程中不停的涉及到字符串的拆分，使用 StringBuilder 装最后的字符串
输入参数 int List<String>
返回值 List<String>

两个指针分别是字符串和结果集的最后一个字母
按照先判断 三个是否相等的规则，然后判断两个是否相等的规则来判断
注意 Java  中 输入流的类 InputStreamReader BufferdReader 的用法
 */

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class StringMatch {

    public List<String> stringMatch(List<String> strs) {
        List<String> ret = new ArrayList<>(strs.size());
        for(String str: strs) {
            ret.add(matchOne(str));
        }

        return ret;
    }

    private static String matchOne2(String str) {
        char[] chs = str.toCharArray();

        // i chs's index
        // idx ret's index
        int idx = 0;
        for (int i = 0; i < chs.length; i++) {
            // match 3 same chracator
            // i >= idx
            if (i > 1 && chs[i] == chs[idx - 1] && chs[idx - 1] == chs[idx - 2]) {
                chs[i] = '\0';
            } else if (i > 2 && chs[i] == chs[idx - 1] && chs[idx - 2] == chs[idx - 3]) {
                chs[i] = '\0';
            } else {
                chs[idx] = chs[i];
                idx ++;
            }
        }
        return String.valueOf(chs, 0, idx);
    }

    /**
     * check and process every word
     *
     * @param str
     * @return
     */
    private String matchOne(String str) {

        int i, i1, i2, i3;
        StringBuilder sb = new StringBuilder();

        char[] strChs = str.toCharArray();
        int idx = 0;
        while (idx < strChs.length) {
            if ((idx + 1 < strChs.length) && strChs[idx] != strChs[idx + 1]) {
                sb.append(strChs[idx]);
                idx ++;

                continue;
            }

            if (idx + 1 == strChs.length) {
                sb.append(strChs[idx]);
                break;
            }

            // idx + 1 <strChs  && strChs[idx] == strChs[idx + 1]
            i = idx;
            i1 = idx + 1;
            // append i i1
            sb.append(strChs[i]);
            sb.append(strChs[i1]);

            // check i2 i3
            i2 = idx + 2;
            while (i2 < strChs.length) {
                if (strChs[i2] == strChs[i1]) {
                    // find first unequal index
                    i2 ++;
                    while (i2 < strChs.length && strChs[i1] == strChs[i2]) {
                        i2 ++;
                    }
                    // i2 == strChs.length
                    if (i2 == strChs.length) {
                        idx = i2;
                        break;
                    }
                } else {
                    // match aabb
                    i3 = i2 + 1;
                    if (i3 < strChs.length && strChs[i3] != strChs[i2]) {
                        sb.append(strChs[i2]);
                        idx = i3;
                        break;
                    } else if (i3 < strChs.length && strChs[i3] == strChs[i2]) {
                        // i == i1  i2 == i3  i2 -> i3
                        i2 = i3;
                    } else {
                        sb.append(strChs[i2]);

                        // 退出到下一个循环注意更改 idx 的下标
                        idx = i3;
                        break;
                    }
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException, NumberFormatException {
//        List<String> ret = (new StringMatch()).stringMatch( Arrays.asList("helloo", "h", "", "hhhhooolll", "helllooohhele"));
//        System.out.println(ret);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            list.add(br.readLine());
        }
        for (int i = 0; i < n; i ++) {
            System.out.println(matchOne2(list.get(i)));
        }
    }
}
