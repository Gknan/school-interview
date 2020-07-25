package year2019;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 底层使用 HashMap + 双向链表实现
 * 设计一个数据结构，实现LRU Cache的功能(Least Recently Used – 最近最少使用缓存)。它支持如下2个操作： get 和 put。
 *
 * int get(int key) – 如果key已存在，则返回key对应的值value（始终大于0）；如果key不存在，则返回-1。
 * void put(int key, int value) – 如果key不存在，将value插入；如果key已存在，则使用value替换原先已经存在的值。如果容量达到了限制，LRU Cache需要在插入新元素之前，将最近最少使用的元素删除。
 *
 * 请特别注意“使用”的定义：新插入或获取key视为被使用一次；而将已经存在的值替换更新，不算被使用。
 *
 * 限制：请在O(1)的时间复杂度内完成上述2个操作。
 */
public class LRUCacheImpl {

    private HashMap<Integer, Node> map = new HashMap<>();
    private LinkedList<Node> list = new LinkedList<>();
    private int cap; // 容量

    public LRUCacheImpl(int capacity) {
        cap = capacity;
    }

    public void put(int key, int value) {
        if (list.size() > cap)
            throw new IllegalArgumentException("Capacity is not right.");

        // 先根据 map 查找是否存在，若存在，取出更新，放到对头；若不存在，查看队列是否满，不满，加到对头；满了，删除最后一个
        // 加到对头
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.val = value; // 直接修改对应的值
//            list.remove(cur);
//            list.addFirst(cur);
            // 如果包含，直接修改
//            map.put(key, cur);
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
//        map.remove(last.val);
        map.remove(last.key);
    }

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

}
