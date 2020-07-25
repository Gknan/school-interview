package qiwang;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
有 N 名工人。 第 i 名工人的工作质量为 quality[i] ，其最低期望工资为 wage[i] 。
现在我们想雇佣 K 名工人组成一个工资组。在雇佣 一组 K 名工人时，我们必须按照下述规则向他们支付工资：
对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
工资组中的每名工人至少应当得到他们的最低期望工资。
返回组成一个满足上述条件的工资组至少需要多少钱。

示例 1：
输入： quality = [10,20,5], wage = [70,50,30], K = 2
输出： 105.00000
解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。

示例 2：
输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
输出： 30.66667
解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
 
提示：
1 <= K <= N <= 10000，其中 N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
与正确答案误差在 10^-5 之内的答案将被视为正确的。

分析：
先按照最小期望工资来排序，拍完序后，分别以第i 个为基准，满足第 i 个人的期望，计算其他人是否满足，求得 k 个人；若在第 i 个人为基准能招够，则
返回结果，否则，继续往下找


先写暴力，满足一个的基础上，计算其他的；并更新最小花费

这里使用最小堆完成了最大堆的功能，特点就在于取了相反数

 */
public class MincostToHireWorkers_857 {

    class Node {
        double quality;
        double wage;
        double wdivq;

        public Node(double quality, double wage) {
            this.quality = quality;
            this.wage = wage;
            this.wdivq = wage / quality;
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Node[] nodes = new Node[quality.length];

        for (int i = 0; i < quality.length; i++) {
            nodes[i] = new Node(quality[i], wage[i]);
        }

        // 按照 wage 排序
//        Arrays.sort(nodes, new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return (int) (o1.wage - o2.wage);
//            }
//        });

//        int cnt = K;

        double globalSum = Integer.MAX_VALUE;
        for (int i = 0; i < quality.length; i++) { // 满足第 i 个的期望，相当于找到了期望标准，以此为标准，遍历所有的元素，计算
            double curWQ = nodes[i].wdivq;
            // 收集所有的满足该标准的元素
//            Node[] temp = new Node[quality.length]; // 收集本次统计的有效值节点
            double[] temp = new double[quality.length];
            int idx = 0;
            for (int j = 0; j < quality.length; j++) { // 遍历所有的找，此标准下的最小值
                double calWage = curWQ * nodes[j].quality;
                if (calWage >= nodes[j].wage) {
//                temp[idx ++] = nodes[j]; // 这里面放的是引用，下面的排序是不是回对其有影响
                    temp[idx++] = calWage;
                    // 若已经找过了 K 个，怎么去根据后面的值更细 sum 呢
                }
            }
            // 根据收集到 的 temp，先判断够 K 个吗，不够进去下一个循环；够，排序后统计当前的 sum
            if (idx >= K) {
                // 排序
                Arrays.sort(temp, 0, idx); // 所有有效的值都参与排序

                // 统计
                double sum = 0;
                for (int j = 0; j < K; j++) {
                    sum += temp[j];
                }

                globalSum = Math.min(globalSum, sum);
            }
        }

        return globalSum;
    }

    // 方法二，按照 wage/quality 横向每个工人的价值，如第 i 个工人的价值 为 5；那么，价值为 5 大的工人就不会被选选；而是在价值比 5 小的
    // 前 i - 1 个个体中选择 K 个，这 K 个得到的 wage 应该最小，所以按照工作的 quality 建立大根堆，找到 K 个最小 quality 的值，然后将
    // sum(quality) * price（价值） 因为价值此时是固定的，所以quality 其实就反应而来 wage的大小
    public double mincostToHireWorkers2(int[] quality, int[] wage, int K) {

        Node[] workers = new Node[quality.length];
        for (int i = 0; i < quality.length; i++) {
            workers[i] = new Node(quality[i], wage[i]);
        }

        Arrays.sort(workers, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.wdivq, o2.wdivq);
            }
        });

        double sum = Integer.MAX_VALUE;
        // 建立大根堆 堆里放 quality
        PriorityQueue<Double> maxHeap = new PriorityQueue<>();
        // 遍历 workers
        double qulitySum = 0;
        for (Node worder: workers) {
            qulitySum += worder.quality;
            maxHeap.add(- worder.quality);

            if (maxHeap.size() > K) {
                qulitySum += maxHeap.poll();
            }

            if (maxHeap.size() == K) {
                sum = Math.min(sum, qulitySum * worder.wdivq);
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        MincostToHireWorkers_857 test = new MincostToHireWorkers_857();

        int[] quality = {3,1,10,10,1};
        int[] wage = {4,8,2,2,7};
        int K = 3;

        System.out.println(test.mincostToHireWorkers2(quality, wage, K));
//        double x = 1e9;
//        double y = Math.pow(10, 9);
//        System.out.println(x);
//        System.out.println(y == x);
//
//        int compare = Integer.compare(1, 3);
//        System.out.println(compare);
    }
}
