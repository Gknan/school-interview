package year2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
2110年美团外卖火星第3000号配送站点有26名骑手，分别以大写字母A-Z命名，因此可以称呼这些骑手为黄家骑士特工A，黄家骑士特工B…黄家骑士特工Z，
某美团黑珍珠餐厅的外卖流水线上会顺序产出一组包裹，美团配送调度引擎已经将包裹分配到骑手，并在包裹上粘贴好骑手名称，如RETTEBTAE代表一组流水
线包裹共9个，同时分配给了名字为A B E R T的5名骑手。请
在不打乱流水线产出顺序的情况下，把这组包裹划分为尽可能多的片段，同一个骑手只会出现在其中的一个片段，返回一个表示每个包裹片段的长度的列表。

输入描述:
输入数据只有一行，为一个字符串(不包含引号)，长度不超过1000，只包含大写字母'A'到'Z'，字符之间无空格。

输出描述:
输出每个分割成片段的包裹组的长度，每个长度之间通过空格隔开

输入例子1:
MPMPCPMCM DEFEGDE HINHKLIN

输出例子1:
9 7 8

例子说明1:
划分结果为MPMPCPMCM,DEFEGDE,HINHKLIN。

每个骑手最多出现在一个片段中。

像MPMPCPMCMDEFEGDE,HINHKLIN的划分是错误的，因为划分的片段数较少。

分析：
指针遍历，并且有 set 记录是否访问过 map （骑手，ArrayList<>出现过的位置） 统一一遍记录所有骑手的出现过的下标
若没有，单独一个派别
若有，将其和上一个一次下标之间的组合并为一组；

遍历一遍；若当前节点划分，是否满足条件，并记录当前遍历过的最远的位置，
 */
public class CutPackeage {


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String s = scanner.nextLine();

        String s = "MPMPCPMCMDEFEGDEHINHKLIN";

        char[] chs = s.toCharArray();

        if (chs.length < 2) {
            System.out.println(chs.length);
            return;
        }

        int[][] map = new int[26][2];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 2; j++) {
                map[i][j] = -1;
            }
        }

        // 记录骑手的第一个和最后一个下标
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            if (map[ch - 'A'][0] == 0) {
                map[ch - 'A'][0] = i;
                map[ch - 'A'][1] = i;
            }
            else {
                // 已经出现过
                map[ch - 'A'][1] = i;
            }
        }

        int [] ret = new int[1000];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = -1;
        }
//        int cut = 0;
        int right = -1; // 当前区域的最优边界
        int k = 0;
        for (int i = 0; i < chs.length; i++) {
            right = Math.max(right, map[chs[i] - 'A'][1]);
            if (i < map[chs[i] - 'A'][1]) {// 右边还有位置
//                right = Math.max(right, map[chs[i] - 'A'][1]);
                continue;
            } else {
                if (i < right) {
                    continue;
                }
                // 找到第一个划分点
                ret[k ++] = i;
//                i ++;
            }
        }

        int preIdx = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (ret[i] == -1) break;
//            System.out.print((ret[i] - preIdx + 1) + " ");
            list.add(ret[i] - preIdx);
//            i ++;
            preIdx = ret[i];
        }

        if (list.size() == 1) {
            System.out.println(list.get(0) + 1);
            return;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            int val = list.get(i);
            if (i == 0) {
                val ++;
            }
            System.out.print(val + " ");
        }
        System.out.println(list.get(list.size() - 1));
    }
}
