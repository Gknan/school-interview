package year2019;

/*
总共有36张牌，每张牌是1~9。每个数字4张牌。
你手里有其中的14张牌，如果这14张牌满足如下条件，即算作和牌
14张牌中有2张相同数字的牌，称为雀头。
除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。顺子的意思是递增的连续3个数字牌（例如234,567等），刻子的意思是相同数字的3个数字牌（例如111,777）


例如：
1 1 1 2 2 2 6 6 6 7 7 7 9 9 可以组成1,2,6,7的4个刻子和9的雀头，可以和牌
1 1 1 1 2 2 3 3 5 6 7 7 8 9 用1做雀头，组123,123,567,789的四个顺子，可以和牌
1 1 1 2 2 2 3 3 3 5 6 7 7 9 无论用1 2 3 7哪个做雀头，都无法组成和牌的条件。

现在，小包从36张牌中抽取了13张牌，他想知道在剩下的23张牌中，再取一张牌，取到哪几种数字牌可以和牌。

分析：
1，写一个喊出，判断给定的 14 张牌是否满足胡牌规则

 */
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CardPlay {

    private static boolean match(int[] map) {
        // cards is sorted
        int [] map_copy;
        int [] map_copy2;

        for (int j = 0; j < 9; j ++) {
            map_copy = map.clone();
            map_copy2 = map.clone();
            if (map_copy[j] >= 2) {
                // choose as que head
                map_copy[j] -= 2;
                map_copy2[j] -= 2;
                boolean flag = matchRest(map_copy) || matchRest2(map_copy2); // differrent order has different answer
                if (flag) {
                    return true;
                }
//                else {
//                    break;
//                }
            }
        }

        return false;
    }

    // first i i+1 i+2 second aaa
    private static boolean matchRest(int[] map) {
        // change delete aaa with delete a a+1 a+2
        // delete i i+1 i+2
        for (int i = 2; i < 9; ) {
            if (map[i] != 0 && map[i - 1] != 0 && map[i - 2] != 0) {
                map[i] --;
                map[i - 1] --;
                map[i - 2] --;
            } else {
                i ++;
            }
        }

        // delete aaa
        for (int i = 0; i < 9; i ++) {
            if (map[i] >= 3) {
                map[i] -= 3;
            }
        }

        for (int i = 0; i < 9; i ++) {
            if (map[i] > 0) {
                return false;
            }
        }

        return true;
    }

    // first aaa second i i+1 i+2
    private static boolean matchRest2(int[] map) {

        // delete aaa
        for (int i = 0; i < 9; i ++) {
            if (map[i] >= 3) {
                map[i] -= 3;
            }
        }

        // delete i i+1 i+2
        for (int i = 2; i < 9; ) {
            if (map[i] != 0 && map[i - 1] != 0 && map[i - 2] != 0) {
                map[i] --;
                map[i - 1] --;
                map[i - 2] --;
            } else {
                i ++;
            }
        }

        for (int i = 0; i < 9; i ++) {
            if (map[i] > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] cards = input.split(" ");
        int[] map = new int[9];

//        ArrayList<Integer> ret = new ArrayList<>();
        int[] ret = new int[9];
        int idx = -1;

        int[] cardsArr = new int[14];
        for (int i = 0; i < 13; i ++) {
            cardsArr[i] = Integer.parseInt(cards[i]);
            map[cardsArr[i] - 1] ++;
        }

        for (int j = 1; j <= 9; j ++) {
            if (4 - map[j - 1] > 0) {
                cardsArr[13] = j;
                map[j - 1] ++;
                if (match(map)) {
//                    ret.add(j);
                    ret[++ idx] = j;
                }
                // 还原现场
                map[j - 1] --;

            }
        }

//        if (ret.size() == 0) {
//            System.out.println(0);
//            return;
//        }
        if (idx == -1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < idx; i ++) {
            System.out.print(ret[i] + " ");
        }
        System.out.print(ret[idx]);

//        for (int i = 0; i < ret.size() - 1; i ++) {
//            System.out.print(ret.get(i) + " ");
//        }
//        System.out.println(ret.get(ret.size() - 1));

    }
}
