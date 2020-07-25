package year2019;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
根据 入店时间建立小根堆
比较堆定，知道不满足；
比较得到的结果从对顶弹出，加入到小根堆
 */
public class FindOrder {

    static class CusterTime {
        int id;
        int start;
        int end;

        public CusterTime(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int search = scanner.nextInt();

//        int n = 10;
//        int search = 20180104;
//        int[][] input = {
//                {1001, 20180103, 20180105},
//                {1002, 20180102, 20180203},
//                {1003, 20180304, 20180306},
//                {1004, 20180401, 20180408},
//                {1005, 20180501, 20180504},
//                {1006, 20180601, 20180604},
//                {1007, 20180705, 20180706},
//                {1008, 20180801, 20180804},
//                {1009, 20180903, 20180903},
//                {1010, 20181003, 20181003},
//
//        };

        PriorityQueue<CusterTime> timeHeap = new PriorityQueue<>(
                new Comparator<CusterTime>() {
                    @Override
                    public int compare(CusterTime o1, CusterTime o2) {
                        return o1.start - o2.start;
                    }
                }
        );

        for (int i = 0; i < n; i++) {
            timeHeap.add(new CusterTime(scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt()));


//            timeHeap.add(new CusterTime(input[i][0],
//                    input[i][1], input[i][2]));
        }

        PriorityQueue<Integer> ansHeap = new PriorityQueue<>();
        boolean flag = false;
        // 出栈比较 start 和 search
        while (!timeHeap.isEmpty() && !flag) {
            CusterTime curTop = timeHeap.poll();
            if (curTop.start > search) {
                flag = true;
                break;
            }
            if (curTop.end >= search) {
                // 收集结果
                ansHeap.add(curTop.id);
            }
        }

        if (ansHeap.isEmpty()) {
            System.out.println("null");
        } else {
            // 这种方式迭代会导致顺序的问题，不可用
//            for (int id : ansHeap) {
//                System.out.println(id);
//            }
            while (!ansHeap.isEmpty()){
                System.out.println(ansHeap.poll());
            }
        }
    }
}
