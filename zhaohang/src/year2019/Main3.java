package year2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/*
公司组织团建活动，到某漂流圣地漂流，现有如下情况：
员工各自体重不一，第 i 个人的体重为 people[i]，每艘漂流船可以承载的最大重量为 limit。
每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
为节省开支，麻烦帮忙计算出载到每一个人所需的最小船只数(保证每个人都能被船载)。

输入描述:
第一行输入参与漂流的人员对应的体重数组，

第二行输入漂流船承载的最大重量

输出描述:
所需最小船只数

输入例子1:
1 2
3

输出例子1:
1

注意：输入根平常的不一样，这里是给了一行数组
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int n = scanner.nextInt();
//        int[] people = new int[n];

//        for (int i = 0; i < people.length; i++) {
//            people[i] = scanner.nextInt();
//        }

        String[] inputStr = scanner.nextLine().split(" ");
        int n = inputStr.length;
        int[] people = new int[n];
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(inputStr[i]);
        }

        int limit = scanner.nextInt();

//        int[] people = {1, 6, 3, 2};
//        int limit = 7;

        // 计算输出需要的最少船只
        int ret = help(people, limit);
        System.out.println(ret);
    }

    /**
     * 所有人都坐船，需要的最少船只
     * @param people
     * @param limit
     * @return
     */
    private static int help(int[] people, int limit) {

        Arrays.sort(people);

        if (people.length > 0 && people[0] > limit) return people.length;
        // 贪心，尽量让 两个数的和为 limit
        int i = 0;
        for (; i < people.length; i++) {
            if (people[i] > limit) {
                break;
            }
        }
        // i 是第一个大于limit的位置
        int end = i - 1;
        // 对 0 ~ i-1 上贪心找
        // 先放进 map
//        HashMap<>
        int ret = 0;
        boolean[] visited = new boolean[i];
//        LinkedList<Integer> queue = new LinkedList<>();
        for (int j = end; j >= 0; j--) {
//            queue.add(people[j]);
            if (visited[j]) continue;
            if (people[j] == limit) {
                ret ++;
            } else {
                // 向前找第一个没有用且和小于等于 limit 的元素
                boolean flag = false;
                for (int k = j - 1; k >= 0; k--) {
                    if (!visited[k] && (people[k] + people[j]) <= limit) {
                        visited[k] = true;
                        ret ++;
                        flag = true;
                        break;
                    }
                }
                // 向前没有找到合适的组合 自己乘船
                if (!flag) {
                    ret ++;
                }
            }
            visited[j] = true;

        }
        return ret;

        // 处理队列
//        int ret = 0;
//        while (!queue.isEmpty()) {
//            int last = queue.pollLast();
//            if (last == limit) {
//                ret ++;
//            } else {
//
//            }
//        }
    }
}
