package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]

剪枝分析：
若 初始串长度大于 12 ，直接返回
若 当前剩余的串长度 len < 当前剩余 k 或者 len > 3*k 剪枝


 */
public class RestoreIpAddresses_93 {

    List<String> retList; // 汇总后返回
    ArrayList<ArrayList<String>> ret; // 收集结果
    public List<String> restoreIpAddresses(String s) {
        retList = new ArrayList<>();
//        if (s.length() < 4) return retList;

        if (s.length() < 4 || s.length() > 12) return retList;

        ret = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        dfs(4, s, path);

        // 整理答案
        if (!ret.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ret.size(); i++) {
                StringBuilder sb = new StringBuilder();
                int j = 0;
                for (; j < 3; j++) {
                    sb.append(ret.get(i).get(j) + ".");
                }
                sb.append(ret.get(i).get(j));
                retList.add(sb.toString());
            }
        }

        return retList;
    }

    /**
     *
     * @param remCnt 剩余的 待匹配段
     * @param remainS 剩余的待匹配子串
     * @param path 走过的路径
     */
    private void dfs(int remCnt, String remainS, LinkedList<String> path) {
        // 剪枝
        int len;
        if ((len = remainS.length()) > 4*remCnt || len < remCnt) return;


        if (remCnt == 0) {
            if (remainS.isEmpty()) { // 到这里，可能 remainS 不为空，此时就不能算一个解
                // 收集结果
                ret.add(new ArrayList<>(path));
                return;
            }
            // 返回结束
            return;
        }

        // remCnt > 0 进行匹配判断
        for (int i = 1; i <= 3; i++) {

            // fail case "010010"    010 被选择了

            // 本次截取 i 个字符作为一段
            // 剪枝
            if (remainS.length() < i) break;

            int curVal;
//            if (((curVal = Integer.valueOf(remainS.substring(0, i))) > 255
//                    || curVal < 0)) break;
            if ((curVal = Integer.valueOf(remainS.substring(0, i))) > 255) break;

//            if (curVal >= Math.pow(10, i - 1) && curVal < Math.pow(10, i)) { // 取出 010 这种错误的解
            if (i == 1 && (curVal > 9)) break;
            if (i == 2 && (curVal < 10 || curVal > 99)) break;
            if (i == 3 && (curVal < 100)) break;

            // 添加到 path
            path.add(remainS.substring(0, i));
            // 这里注意更新时 使用的而是 i
            dfs(remCnt - 1, remainS.substring(i), path);
            // 还原现场
            path.removeLast();
//            }
        }

    }

    public static void main(String[] args) {
        RestoreIpAddresses_93 test = new RestoreIpAddresses_93();
        String s = "010010";
        System.out.println(test.restoreIpAddresses(s));
    }
}
