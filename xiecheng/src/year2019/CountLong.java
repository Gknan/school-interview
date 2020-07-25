package year2019;

import java.util.Scanner;

public class CountLong {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long target = scanner.nextLong();

        // 统计其中 1 的个数
        int k = 0;
        while (target != 0) {
            k ++;
            target &= (target - 1);
        }

        System.out.println(k);
    }
}
