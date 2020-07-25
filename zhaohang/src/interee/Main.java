package interee;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static HashMap<Character, Character> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String[] inputs = new String[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = scanner.next();
        }

        map.put('1','1');
        map.put('2','5');
        map.put('3','8');
        map.put('4','7');
        map.put('6','9');
        map.put('5','2');
        map.put('8','3');
        map.put('7','4');
        map.put('9','6');

        for (int i = 0; i < n; i++) {
            if (isValid(inputs[i])) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * 判断输入字符串是否是回文镜像
     * @param str
     * @return
     */
    private static boolean isValid(String str) {

        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length / 2; i++) {
            char front = chs[i];
            char post = chs[chs.length - 1 - i];

            if (!valid(front, post)) {
                return false;
            }
        }

        if (chs.length % 2 == 1) {
            // 奇数个 中间需要单独判断
            if (chs[(chs.length / 2)] != '1') return false;
        }

        return true;
    }

    /**
     * 判断 front 和 post 字符是否镜像
     * @param front
     * @param post
     * @return
     */
    private static boolean valid(char front, char post) {
        return post == map.get(front);
    }
}
