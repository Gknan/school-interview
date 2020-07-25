package year2019;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int cap = scanner.nextInt();

        LRUCacheImpl2 lru = new LRUCacheImpl2(cap);
//
//        lru.put(1, 1);
//        lru.put(2, 2);
//        lru.put(3, 3);
//        lru.put(4, 4);
//        System.out.println(lru.getSize());

//        LRUCache lru = new LRUCache(cap);

        if (cap <= 0) return;

        while (scanner.hasNextLine()) {
            String op = scanner.next();
            if ("p".equals(op)) {
                // 插入
                lru.put(scanner.nextInt(), scanner.nextInt());
            } else if ("g".equals(op)) {
                int val = lru.get(scanner.nextInt());
                System.out.println(val);
            }
        }

    }
}

class LRUCache {

    int size;
    LinkedList<Integer> linkedList;
    Map<Integer, Integer> map;

    public LRUCache(int size) {
        this.size = size;
        linkedList = new LinkedList<>();
        map = new HashMap<>();
    }

//    public int get(int key) {
//        if (!map.containsKey(key)) return -1;
//        linkedList.remove(Integer.valueOf(key));
//        linkedList.addFirst(key);
//        return map.get(key);
//    }

    public int get(int key) {
        // 获取制定 key 的值，若不存在，返回 -1；若存在，返回结果，该节点移动到最前面
        if (linkedList.size() == 0)
            return -1;

        if (!map.containsKey(key)) {
            return -1;
        }


        linkedList.remove(Integer.valueOf(key));
        linkedList.addFirst(key);
        return map.get(key);
    }

//    public void put(int key, int value) {
//        if (map.containsKey(key)) {
//            map.put(key, value);
//            return;
//        }
//        map.put(key, value);
//        linkedList.addFirst(key);
//        if (linkedList.size() > this.size) {
//            int last = linkedList.removeLast();
//            map.remove(last);
//        }
//        linkedList.addFirst(key);
//        map.put(key,value);
//    }

    public void put(int key, int value) {
        // 先根据 map 查找是否存在，若存在，取出更新，放到对头；若不存在，查看队列是否满，不满，加到对头；满了，删除最后一个
        // 加到对头
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            // 如果 size 是0 的话，removeLast 就会报错 是 0 个元素，先 普通 进去，再删除是合法的
            // 先删除就会报错
            // 报错的位置
            if (linkedList.size() == size) {
                int last = linkedList.removeLast();
                map.remove(last);
            }
            linkedList.addFirst(key);
            map.put(key,value);
        }
    }

}

class LRUCacheImpl2 {

    private HashMap<Integer, Node> map = new HashMap<>();
    private LinkedList<Node> list = new LinkedList<>();
    private int cap; // 容量

    public LRUCacheImpl2(int capacity) {
        cap = capacity;
    }

    public void put(int key, int value) {
        // 先根据 map 查找是否存在，若存在，取出更新，放到对头；若不存在，查看队列是否满，不满，加到对头；满了，删除最后一个
        // 加到对头
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.val = value; // 直接修改对应的值
            // 如果包含，直接修改
        } else {
            Node newNode = new Node(key, value);

//            if (list.size() == cap) {
//                // 满了
//                Node last = list.removeLast();
//                map.remove(last.key);
//            }
            list.addFirst(newNode);
            map.put(key, newNode);

            if (list.size() > cap) {
                // 满了
                Node last = list.removeLast();
                map.remove(last.key);
            }
        }
    }

    public int get(int key) {
        // 获取制定 key 的值，若不存在，返回 -1；若存在，返回结果，该节点移动到最前面
        if (list.size() == 0)
            return -1;

        if (!map.containsKey(key)) {
            return -1;
        }

        Node cur = map.get(key);
        // 取出并放到对首
        list.remove(cur);
        list.addFirst(cur);

        return cur.val;
    }

    class Node {
        public int key;
        public int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}
