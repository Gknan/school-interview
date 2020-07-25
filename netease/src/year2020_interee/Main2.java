package year2020_interee;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 小易在维护数据的时候遇到一个需求，具体来说小易有一系列数据，这些数据了构成一个长度为n的数字序列，接下来小易会在这个序列上进行q次操作。
 * 每次操作有一个查询的数字x，小易需要将序列数据中所有大于等于x的数字都减一，并输出在本次操作中有多少个数字被减一了。
 * 小易犯了难，希望你能帮帮他。
 *
 * 输入描述:
 * 第一行n,q，表示数字个数和操作个数。
 * 接下来一行n个数表示初始的数字。
 * 接下来q行，每行一个数，表示指定的数字x。
 * ，
 *
 * 输出描述:
 * 对于每个询问，输出一个数字表示答案
 *
 * 全部遍历找结果
 */
public class Main2 {

    public static class Pair {
        public int val;
        public boolean visited;

        public Pair(int val, boolean visited) {
            this.val = val;
            this.visited = visited;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] arr = new int[n];
        int[] qArr = new int[q];

        int[] nums = new int[200001];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            nums[arr[i]] ++;
        }

        // 汇总 nums
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] += sum;
            sum = nums[i];
        }


        for (int i = 0; i < qArr.length; i++) {
            qArr[i] = scanner.nextInt();
        }
//
//        int[] arr = {1, 2, 3, 4};
//        int[] qArr = {4, 3, 1};


//        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
//            @Override
//            public int compare(Pair o1, Pair o2) {
//                return o2.val - o1.val;
//            }
//        });
//        // 构建大根堆
//        for (int i = 0; i < arr.length; i++) {
//            maxHeap.add(new Pair(arr[i], false));
//        }
        for (int i = 0; i < qArr.length; i++) {
            int search = qArr[i];

            System.out.println(nums[search]);

            // 更新 nums 数组 完成

//            int cnt = 0;
//            for (int j = 0; j < arr.length; j++) {
//                if (arr[j] >= search) {
//                    arr[j]--;
//
//                    cnt  ++;
//                }
//            }

//            System.out.println(cnt);

//            // 查询修改最大堆，并计数
//            int cnt = 0;
//            // 1 2 2 2 时， x 为 1 ，maxHeap 错误
//            while (!maxHeap.isEmpty() && !maxHeap.peek().visited && maxHeap.peek().val >= search) {
//                cnt ++;
//
//                Pair top = maxHeap.poll();
//                top.visited = true;
//                top.val --;
//                maxHeap.add(top);
//            }
//
//            // 重置所有元素的 visited 字段
//            PriorityQueue<Pair> helpHeap = new PriorityQueue<>(new Comparator<Pair>() {
//                @Override
//                public int compare(Pair o1, Pair o2) {
//                    return o2.val - o1.val;
//                }
//            });
//
//            while (!maxHeap.isEmpty()) {
//                Pair poll = maxHeap.poll();
//                poll.visited = false;
//                helpHeap.add(poll);
//            }
//            maxHeap = helpHeap;

//            System.out.println(cnt);
        }
    }
}
