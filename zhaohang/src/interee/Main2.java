package interee;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static long cnt;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String nums = scanner.next();
//            int k = scanner.nextInt();

            long k = scanner.nextLong();

//            int ret = findAns(nums, k);

            long ret = findAns(nums, k);

            System.out.println(ret);
        }

//        int test = Character.digit('3', 10);
//        System.out.println(test);

//        int [] arr = {2, 1, 3, 4, 6, 2};
//        System.out.println(count(2, 4, arr));
    }

    /**
     * 找 nums 中添加 + - 号可以得到的结果
     * 在里面添加加减号
     * @param nums
     * @param k
     * @return
     */
//    private static int findAns(String nums, int k) {
    private static long findAns(String nums, long k) {
        char[] chs = nums.toCharArray();

//        int input = Integer.valueOf(nums);
        
        int[] digits = new int[nums.length()];

//        int i = nums.length() - 1;
//        while (input != 0) {
//            digits[i --] = input % 10;
//            input /= 10;
//        }
        for (int i = 0; i < chs.length; i++) {
            digits[i] = Character.digit(chs[i], 10);
        }
//        Arrays.sort(digits);

        cnt = 0;
        backtrack(digits, 1, k, digits[0]);
//        backtrack(digits, 0, k, 0);

        return cnt;
    }

//    static int cnt;

//    private static void backtrack(int[] digits, int idx, int k, int preSum) {
    private static void backtrack(int[] digits, int idx, long k, int preSum) {

        if (idx == digits.length) {
            if (preSum == k) {
                cnt ++;
            }
            return;
        }

        for (int i = idx; i < digits.length; i++) {
            int cur = count(idx, i, digits);
            backtrack(digits, i + 1, k, preSum - cur);
            backtrack(digits, i + 1, k, preSum + cur);
        }

//        backtrack(digits, idx + 1, k, preSum + digits[idx]);
//        backtrack(digits, idx + 1, k, preSum - digits[idx]);
    }

    /**
     * digits 中 start 到 end 的统计结果
     * @param start
     * @param end
     * @param digits
     * @return
     */
    private static int count(int start, int end, int[] digits) {

        int ret = 0;
        int up = 10;
        for (int i = start; i <= end; i++) {
            ret = ret * up + digits[i];
        }

        return ret;
    }


}
