package year2017;

import java.util.Scanner;

public class SetASetB {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] map = new int[10001];
        while (scanner.hasNext()) {
            initMap(map);
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            for (int i = 0; i < m; i++) {
                map[scanner.nextInt()] = 1;
            }

            for (int i = 0; i < n; i++) {
                map[scanner.nextInt()] = 1;
            }

//            int lastIdx = 1;
//            for (int i = 1; i < map.length; i++) {
//                if (map[i] != 0) lastIdx = i;
//            }

            for (int i = 1; i <= map.length; i++) {

                if (map[i] != 0) {
                    System.out.print(i + " ");
                }
            }
//            System.out.println(lastIdx + " ");
        }
    }

    private static void initMap(int[] map) {
        for (int i = 0; i < map.length; i++) {
            map[i] = 0;
        }
    }
}
