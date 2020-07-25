import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/*
aaa bcd 非递减

已知 n 个片段 字符串 怎么利用其中的几个拼出最长的长度

输入：第一行是 n 代表个数 就下来 n 行代表输入的 字符串
输出：最长长度

如 n aaa  bcd
输出  6

分析：
1，记录每个字符串的 第一个和最后一个字符，并记录 该字符串中的长度
2，回收所有的可选队列，记录结果

能够剪枝

注意的是；
接收输入使用 ReaderBuffer 包装  InputStream 自己转换类型，避免 Scanner 的问题
 */
public class PlayPiano {

    int ret = 0;
    public int getLongestStr(String[] inputs) {
        process(inputs, 0, new LinkedList<String>());
        return ret;
    }

    private void process(String[] inputs, int cur, LinkedList<String> pre) {
        if (cur == inputs.length) {
            int cnt = 0;
            System.out.println(pre);
            for(String s: pre) {
                cnt += s.length();
            }
//            ret = Math.min(ret, cnt);
//            ret = Math.max(ret, cnt);
            ret = Math.max(ret, cnt);

        }

        // 因为拍完序了，所以每次从这里当前位置开始，不能从头开始
        for (int i = cur; i < inputs.length; i++) {
//            if (pre.isEmpty() || isValid(inputs[i], pre.getLast())) {
            if (pre.isEmpty() || isValid(pre.getLast(), inputs[i])) {
                pre.add(inputs[i]);
                process(inputs, i + 1, pre);
                pre.removeLast();
            }
        }
    }

    // 判断两个字符串组成一起是否是 非递减的
    private boolean isValid(String first, String next) {
        // 只要前一个字符串的最后一个字符小于等于 后一个字符串的第一个字符 就返回true
        return first.charAt(first.length() - 1) <= next.charAt(0);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] inputs = new String[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = br.readLine();
        }

//        System.out.println(Arrays.toString(inputs));


        // 先按照 字典序给string 排序
        Arrays.sort(inputs);
//        System.out.println(Arrays.toString(inputs));

        // for test
        System.out.println(new PlayPiano().getLongestStr(inputs));
    }
}
