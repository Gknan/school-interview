package top100;


import java.util.*;

/*
假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，
k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

分析：
回溯法：
遍历数组，set 记录已经访问过的元素，对于 i 下标的元素，检查合法性；
合法性的定义，k 是否满足条件，满足则选中下一步，不满足跳过这个元素
HashMap<> 记录选中的路径上 大于等于 key 的值的个数
由于 n 的个数小于 1100 ，所以直接初始化一个 1100 大小的HashMap

贪心法：
对于身高最高的人而言，他们之间的相对顺序是固定的，因为身高低于最高的人对于他们是不可见的
所以按照升高降序先排列；身高相同的按照 k 的顺序递增排序
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
得到：
[[7,0], [7,1],  [6,1], [5,0],[5,2], [4,4]]
然后再遍历，按照 k 把每个元素插入到正确的位置
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

时间复杂度：快排 O(nlogn) 插入排序 O(n2)
空间复杂度：O(N) 队列


 */
public class ReconstructQueue_406 {

    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] == o2[0]) ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> list = new LinkedList<>();
        for (int[] p: people) {
            // add(index, object) 指定位置插入
            list.add(p[1], p);
        }

        // 穿入的是个结果数组
        return list.toArray(new int[people.length][2]);



//        ArrayList<int[]> list = new ArrayList<>();
//        HashMap<Integer, Integer> id2H = new HashMap<>();
//        HashSet<int[]> visited = new HashSet<>();
//
//        reconstruct(people, list, id2H, visited, 0);
//        return null;
    }

    private void reconstruct(int[][] people, ArrayList<int[]> list, HashMap<Integer, Integer> id2H, HashSet<int[]> visited, int start) {
        if (visited.size() == people.length) {
            return;
        }

        for (int i = 0; i < people.length; i ++) {
            // 之前没有访问过且 该大于该位置的个数小于等于他之前的总人数
//            if (!visited.contains(people[i]) && people[i][1] <= i
//                    && h2Cnt.get(people[i][0]) == people[i][1]) {
//                // 遍历一遍统计当前位置的 h 之前有多少大于等于 h 的元素
//                // 当前位置合法 更新 visited h2Cnt list
//                list.add(people[i]);
//                visited.add(people[i]);
////                h2Cnt.put(people[i][0], h2Cnt.get())
//            }
        }
    }
}
