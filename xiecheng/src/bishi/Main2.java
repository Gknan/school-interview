package bishi;

import java.util.*;

/*
携程海洋馆中有 n 只萌萌的小海豚，初始均为 0 岁，每只小海豚的寿命是 m 岁，
且这些小海豚会在 birthYear[i] 这些年份生产出一位宝宝海豚（1 <= birthYear[i] <= m）
，每位宝宝海豚刚出生为 0 岁。

问 x 年时，携程海洋馆有多少只小海豚？

n（初始海豚数）

m（海豚寿命）

海豚生宝宝的年份数量(假设为p)

海豚生宝宝的年份1

...

海豚生宝宝的年份p

x（几年后）

输出
x年后，共有多少只小海豚


样例输入
5
5
2
2
4
5
样例输出
20
 */
public class Main2 {

    static class Dol {
        public int age;
        public boolean isAlive;

        public Dol(int age, boolean isAlive) {
            this.age = age;
            this.isAlive = isAlive;
        }
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);


//        int n = scanner.nextInt();// num of dop
//        int m = scanner.nextInt();//寿命
//        p = scanner.nextInt();
//        pArr = new int[p];
//
//        HashSet<Integer> set = new HashSet<>();
//        for (int i = 0; i < p; i++) {
//            pArr[i] = scanner.nextInt();
////            map.put()
//        }
//        int x = scanner.nextInt();

        int n = 5;// num of dop
        int m = 9;//寿命
        p = 2;
        int[] pArr = {2, 4};

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < p; i++) {
            set.add(pArr[i]);
        }
//        for (int i = 0; i < p; i++) {
//            pArr[i] = scanner.nextInt();
////            map.put()
//        }
//        int x = scanner.nextInt();

        int x = 5;

        List<Dol> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Dol(0, true));
        }
        // m 年 内更新list
        for (int i = 1; i <= m; i++) {
            // 每一年 都更新所有海豚的年龄和 alive 字段
            ArrayList<Dol> newDols = new ArrayList<>();
            for (Dol dol: list) {
                if (!dol.isAlive) continue;

                dol.age ++;

                // 有新生的宝宝吗
                if (set.contains(dol.age)) {
                    newDols.add(new Dol(0, true));
                }

                // 是否死亡
                if (dol.age == m) dol.isAlive = false;
            }
            if (newDols.size() > 0) {
                list.addAll(newDols);
            };
        }

        // 只统计或者的海豚
        int sum = 0;
        for (Dol dof: list) {
            if (dof.isAlive) sum += 1;
        }

        System.out.println(sum);
//        System.out.println(list.size());


//        map.put(0, n);// 最开始 0 岁的海豚有 n 只
//
//        // 收集 map 中的结果即可
//        backtrack(0, m);
    }

    static HashMap<Integer, Integer> map = new HashMap<>(); // 年龄 为 kye 的海豚有多少只
    static int[] pArr;
    static int p;
    public static void backtrack(int start, int m) {
        // 第 start 年，海豚的更新

        if (start == m) {
            return;
        }

        // 去年所有海豚 + 1岁
        //  map 遍历的同时修改
        for(int key: map.keySet()) {
            if (key == m) map.remove(key);
            else {
                Integer lastCnt = map.remove(key);
                map.put(key + 1, lastCnt);
            }
        }
        // 更新新生长海豚的信息
        for (int i = 0; i < p; i++) {
//            for (int lastGrage: map.keySet()) {
//                // 去年的海豚年龄
//                if (lastGrage == )
//            }
            //  pArr[i] 的海豚，生产 年龄为 0 的同数量的宝宝，自己的年龄加1
            if (map.containsKey(pArr[i])) {
                map.put(0, map.get(0) + map.get(pArr[i]));
            }
        }

        backtrack(start + 1, m);
    }
}
