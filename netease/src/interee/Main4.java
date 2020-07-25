package interee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Main4 {

    public static class Pair {
        public int defence;
        public int harm;

        public Pair(int defence, int harm) {
            this.defence = defence;
            this.harm = harm;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int D = scanner.nextInt();

        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(scanner.nextInt(), 0);
        }
        for (int i = 0; i < n; i++) {
            pairs[i].harm = scanner.nextInt();
        }

        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.defence - o2.defence;
            }
        });

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (D < pairs[i].defence) {
                sum += pairs[i].harm;
            }
            D ++;
        }

        System.out.println(sum);
    }
}
