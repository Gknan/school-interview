package interee;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*

 */
public class Main {

    public static void help(int[] arr, int k, int m) {
        int n = arr.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < n; i++)
            queue.add(arr[i] + m * k); //先全部加上m*k
        int index = m - 1;
        while (m-- > 0 && !queue.isEmpty()) {
            int x = queue.poll();
            x = (x - index * k) / 2 + index * k;  //每次减去index*k
            queue.add(x);
            index--;
        }
        int ans = 0;
        for (int x : queue)
            ans += x;
        System.out.println(ans);

    }

    public static void help2(int[] arr, int k, int m) {

        int n = arr.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < n; i++)
            queue.add(arr[i]); //先全部加上m*k


        for (int i = 1; i <= m; i++) {
            Integer poll = queue.poll();
            poll -= (poll + i * k) / 2;
            queue.add(poll);
        }

        int ans = 0;
        for (int x : queue)
            ans += x;
        System.out.println(ans + m * n * k);

    }

    /*
    for (int i = 0; i < n; i++){
            cin >> chick;
            que.push(chick);
    }
    for (int i = 1; i <= m; i++){
        long long now = que.top();
        que.pop();
        now -= (now+i*k)/2;
        que.push(now);
    }
    long long sum = 0;
    while (!que.empty())
    {
        sum += que.top();
        que.pop();
    }
    cout << sum + n*m*k << endl;
    return 0;
     */

    public static void main(String[] args) {
        int[] arr = {100, 200, 400};
        int m = 3;
        int k = 100;
        help(arr, k, m);
        help2(arr, k, m);
    }


    public void method() {
        //        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt(); // n 个鸡场3
//        int m = scanner.nextInt(); // m 天后
//        int k = scanner.nextInt(); // 每天增加的积
//
////
////
//        int[] arr = new int[n];// 鸡儿的初始值

//
//        for (int i = 0; i < n; i++) {
//            arr[i] = scanner.nextInt();
//        }

//
        int n = 3;
        int m = 3;
        int k = 200;
        int[] arr = {500, 400, 400};
//
//

        // 如果有多个养鸡场都是相等，都要减

        // 计算结果
        int ret = 0;
        for (int i = 0; i < m; i++) {
            // 求和当前的鸡场
            int sum = 0; //
            int max = 0;
//            int idx = 0;
            for (int j = 0; j < n; j++) {
                arr[j] += k; // update arr[i]
                sum += arr[j];
                max = Math.max(arr[j], max);
//                if (arr[j] > max) {
//                    idx = j;
//                    max = arr[j];
//                }
            }
            // update arr idx
//            arr[idx] /= 2;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == max) {
                    arr[j] /= 2;
                    cnt++;
                }
            }

            ret = sum - cnt * (max / 2);
        }

        System.out.println(ret);
    }
}
