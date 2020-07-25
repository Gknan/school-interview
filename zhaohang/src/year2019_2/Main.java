package year2019_2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 求好数
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int ret = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                ret ++;
            }
        }

        System.out.println(ret);

    }



    static HashSet<Integer> valid = new HashSet<>(Arrays.asList(
            1, 2, 5, 6, 8, 9, 0
    ));
    static HashSet<Integer> difValid = new HashSet<>(Arrays.asList(
            2, 5, 6, 9
    ));
    /**
     * 判断 num 是否是好数，好数，每个位置的数字都可以旋转 180 度，且旋转后的
     * 值不等于原来的值
     * @param num
     * @return
     */
    public static boolean isGood(int num) {

        // 只要有一个位置旋转后无效，返回 false
        // 所有位置旋转后有效，且 至少有一个位置是 3 6  2 5 之一，返回 ture

        boolean flag = false;
        while (num != 0) {
            int cur = num % 10;
            if (!valid.contains(cur)) {
                return false;
            }
            if (difValid.contains(cur)) {
                flag = true;
            }

            num /= 10;
        }

        return flag ? true : false;
    }
}
