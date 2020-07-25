import java.util.*;

public class Dianhuaanjian {

    public static HashMap<Character, List<Character>> map;

    public static void main(String[] args) {


        map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();

//        String input = "23";
        char[] chs = input.toCharArray();
        ArrayList<String> ret = help(chs);

        System.out.println(ret);
    }

    static ArrayList<String> retList = new ArrayList<>();
    private static ArrayList<String> help(char[] chs) {

        backtrack(chs, 0, new LinkedList<Character>());

        return retList;
    }

    private static void backtrack(char[] chs, int idx,
                                  LinkedList<Character> path) {

        if (idx == chs.length) {
            // 统计到头，收集结果
            StringBuilder stringBuilder = new StringBuilder();
            for (char ch: path) {
                stringBuilder.append(ch);
            }

            retList.add(stringBuilder.toString());
            return;
        }

        // 遍历当前数字对应的位置，就是可以的选择
        for (char ch: map.get(chs[idx])) {
            path.add(ch);
            backtrack(chs, idx + 1, path);
            path.removeLast();
        }
    }


}
