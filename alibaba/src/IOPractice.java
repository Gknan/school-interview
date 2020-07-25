import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 输入数据包括多组。
public class IOPractice {
//
    public void method1() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a + b);
        }


    }

    // 第一行是 n 接下来有 n 行 每行两个数字哦
    public void method2() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] arr = new int[n][2];
        int i = 0;
        while (i < n) {
            arr[i][0] = scanner.nextInt();
            arr[i][1] = scanner.nextInt();
            i ++;
        }

        for (int j = 0; j < n; j++) {
            System.out.println(arr[j][0] + arr[j][1]);
        }
    }

    // 输入有多组，输入为 0 0 结束输入
    public void method3() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a == 0 && b == 0) break;
            System.out.println(a + b);
        }
    }

    // 每行第一数字是个数；接下来是求和的每个数字 当第一个为 0 时，结束
    public void method4() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            if (n == 0) break;
            int sum = 0;
            while (n-- > 0) {
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
    }

    // 第一行输入接下来的行数 接下来 每一行第一个是这一行求和的个数，
    public void method5() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        while (n -- > 0) {
            int m = scanner.nextInt();
            int ret = 0;
            while (m -- > 0) {
                ret += scanner.nextInt();
            }

            System.out.println(ret);
        }

    }

    // 多组数据，每行第一个为这一组的个数
    public void method6() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int sum = 0;
            while (n -- > 0) {
                sum += scanner.nextInt();
            }

            System.out.println(sum);
        }
    }

    // 每行数据不提前知道 空格隔开
    public void method7() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String[] s = scanner.nextLine().split(" ");
            int sum =  0;
            for (int i = 0; i < s.length; i++) {
                sum += Integer.valueOf(s[i]);
            }
            System.out.println(sum);
        }
    }

    // 第一行是 n ，第二行是 n 个空格隔开的字符
    public void method8() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = scanner.next();
        }

        Arrays.sort(strs);

        for (int i = 0; i < n - 1; i++) {
            System.out.print(strs[i] + " ");
        }

        System.out.println(strs[n - 1]);
    }

    // 多个测试用例，多个测试用例一行；行内空格隔开
    public void method9 () {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 处理当前行
            String[] s = scanner.nextLine().split(" ");
            Arrays.sort(s);
            int i = 0;
            for (; i < s.length - 1; i++) {
                System.out.print(s[i] + " ");
            }

            System.out.println(s[i]);
        }
    }

    // 多个测试用例，多个测试用例一行；行内空格隔开
    public void method10 () {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 处理当前行
            String[] s = scanner.nextLine().split(",");
            Arrays.sort(s);
            int i = 0;
            for (; i < s.length - 1; i++) {
                System.out.print(s[i] + ",");
            }

            System.out.println(s[i]);
        }
    }

    public void method11 () {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] arr = new int[3][n];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        System.out.println(1);

    }

    public void method12 () {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();// 列
        int q = scanner.nextInt();

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        ArrayList<int[]> list = new ArrayList<>();
        while (q -- > 0) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            list.add(new int[]{row, col});
        }

//        help(arr, list);
    }

    public static void main(String[] args) {
        new IOPractice().method11();
    }
}

