package year2017;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class SaiMaIOPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            HashSet<Integer> mems = new HashSet<>();
            mems.add(1);
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();
                if (c == 1) {
//                    map.put(a, b);
//                    map.put(b, a);// a 和 b 是老乡，则 b 和 a 是老乡

                }
            }
            // 统计老乡数
            int cnt = 0;
            int key = 1;
            HashSet<Integer> visited = new HashSet<>();
            while (map.containsKey(key)) {
//                if (visited.contains(key))
//                visited.add(key);
                cnt ++;
                Integer next = map.get(key);
                key = next;
            }

            System.out.println(cnt);
        }
    }
}

class UnionFind {

    public static class Node {
        // whatever you like
        int id;
    }

    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSetSize= sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }

    }

}
