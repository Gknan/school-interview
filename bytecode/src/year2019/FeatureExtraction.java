package year2019;

/*
一个猫咪特征是一个两维的vector<x, y>。如果x_1=x_2 and y_1=y_2，那么这俩是同一个特征。
如果特征<a, b>在持续帧里出现，那么它将构成特征运动。比如，特征<a, b>在第2/3/4/7/8帧出现，那么该特征将形成两个特征运动2-3-4 和7-8。

输入描述:
第一行包含一个正整数N，代表测试用例的个数。

每个测试用例的第一行包含一个正整数M，代表视频的帧数。

接下来的M行，每行代表一帧。其中，第一个数字是该帧的特征个数，接下来的数字是在特征的取值；比如样例输入第三行里，2代表该帧有两个猫咪特征，<1，1>和<2，2>
所有用例的输入特征总数和<100000

N满足1≤N≤100000，M满足1≤M≤10000，一帧的特征个数满足 ≤ 10000。
特征取值均为非负整数。

输出描述:
对每一个测试用例，输出特征运动的长度作为一行

分析：
1，对于测试用例1 map(frame: map(feature: cnt))
如 {1: {1_1_: 1, 1_2_: 1}}
对于每一帧，比较与上一帧的关系更新 cnt
最后遍历所有 cnt 输出结果

说明
特征<1,1>在连续的帧中连续出现3次，相比其他特征连续出现的次数大，所以输出3
备注:
如没有长度大于2的特征运动，返回1
 */

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FeatureExtraction {

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<HashMap<Integer, HashMap>> data = new ArrayList<>();
//        ArrayList ret = new ArrayList<>(N);

        for (int i = 0; i < N; i ++) {
            HashMap<Integer, HashMap> map = new HashMap<>();
            int frames = Integer.parseInt(br.readLine());
            for (int j = 0; j < frames; j ++) {
                String[] frame = br.readLine().split(" ");
                int featCnt = Integer.parseInt(frame[0]);
//                int idx = 1;
                HashMap<String, Integer> innerMap = new HashMap<>();
                // notice: add all the ele to map
//                for (int k = 0; k < featCnt; k ++) {
//                    innerMap.put(frame[idx] + "_" + frame[idx + 1], 1);
//                }
                for (int k = 0; k < featCnt; k ++) {
                    innerMap.put(frame[2*k + 1] + "_" + frame[2*k + 2], 1);
                }
                map.put(j, innerMap);
            }
            data.add(map);
        }

        for (int i = 0; i < N; i ++) {
            int cnt = process(data.get(i));
            if (cnt < 2) {
                System.out.println(1);
            } else {
                System.out.println(cnt);
            }
        }

    }

    private static int process(HashMap<Integer, HashMap> map) {
        int maxCnt = Integer.MIN_VALUE;

        // update cnt for this object
        for (int i = 1; i < map.size(); i ++) {
            HashMap<String, Integer> innerMap = map.get(i);
            HashMap<String, Integer> lastInnerMap = map.get(i - 1);
            // if the map is structurally modified at any time after
            // * the iterator is created, in any way except through the iterator's own
            // * <tt>remove</tt> method,
            for (String key: innerMap.keySet()) {
                if (!lastInnerMap.keySet().isEmpty()
                        && lastInnerMap.keySet().contains(key)) {
                    innerMap.put(key, lastInnerMap.get(key) + 1);

                }
                maxCnt = Math.max(maxCnt, innerMap.get(key));
            }
        }

        // find max cnt


        return maxCnt;
    }
}
