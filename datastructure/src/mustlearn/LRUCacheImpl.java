package mustlearn;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 底层使用 HashMap + 双向链表实现
 */
public class LRUCacheImpl implements LRUCache {

    private HashMap<Integer, Node> map = new HashMap<>();
    private LinkedList<Node> list = new LinkedList<>();
    private int cap; // 容量

    public LRUCacheImpl(int capacity) {
        cap = capacity;
    }

    @Override
    public void put(int key, int value) {
        if (list.size() > cap)
            throw new IllegalArgumentException("Capacity is not right.");

        // 先根据 map 查找是否存在，若存在，取出更新，放到对头；若不存在，查看队列是否满，不满，加到对头；满了，删除最后一个
        // 加到对头
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.val = value;
            list.remove(cur);
            list.addFirst(cur);
        } else {
            Node newNode = new Node(key, value);
            if (list.size() == cap) {
                // 满了
                deleteLast();
            }
            list.addFirst(newNode);
            map.put(key, newNode);
        }
    }

    // 删除最后一个节点
    private void deleteLast() {
        Node last = list.removeLast();
        // 取消和 map 的关系
        map.remove(last.val);
    }

    @Override
    public int get(int key) {
        // 获取制定 key 的值，若不存在，返回 -1；若存在，返回结果，该节点移动到最前面
        if (list.size() == 0)
            throw new IllegalArgumentException("Cache is empty, cant get something.");

        if (!map.containsKey(key)) {
            return -1;
        }

        Node cur = map.get(key);
        // 取出并放到对首
        list.remove(cur);
        list.addFirst(cur);

        return cur.val;
    }

    /**
     * 删除某个元素
     * @param key
     * @return
     */
    @Override
    public int remove(int key) {
        if (list.size() == 0)
            throw new IllegalArgumentException("Cache is empty, cant get something.");

        if (!map.containsKey(key)) {
            return -1;
        }


        Node cur = map.get(key);

        list.remove(cur);
        map.remove(cur.val);

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

    public static void main(String[] args) {
        LRUCacheImpl cat = new LRUCacheImpl(3);

        cat.put(1, 1);
        cat.put(2, 2);
        System.out.println(cat.get(1));
        System.out.println(cat.get(1));

        cat.put(3, 3);
        cat.put(4, 4);
        System.out.println(cat.get(2));
        cat.remove(4);
        System.out.println(cat.get(4));
    }

}
