package interee;

import java.util.HashSet;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();// 家庭
        int idx = scanner.nextInt(); // 携带病毒编号

        HashSet<Integer> set = new HashSet<>();
        set.add(idx);

        // 二次传染？？？
        for (int i = 0; i < m; i++) {
            int cnt = scanner.nextInt();
            int[] arr = new int[cnt];
            boolean flag = false;
            for (int j = 0; j < cnt; j++) {
                arr[j] = scanner.nextInt();
                if (set.contains(arr[j])) {
                    flag = true;
                }
            }
            if (flag) {
                for (int j = 0; j < cnt; j++) {
                    set.add(arr[j]);
                }
            }

        }

        System.out.println(set.size());
    }
}
