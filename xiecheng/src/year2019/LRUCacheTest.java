package year2019;

/*
设计一个数据结构，实现LRU Cache的功能(Least Recently Used – 最近最少使用缓存)。它支持如下2个操作： get 和 put。

int get(int key) – 如果key已存在，则返回key对应的值value（始终大于0）；如果key不存在，则返回-1。
void put(int key, int value) – 如果key不存在，将value插入；如果key已存在，则使用value替换原先已经存在的值。如果容量达到了限制，LRU Cache需要在插入新元素之前，将最近最少使用的元素删除。

请特别注意“使用”的定义：新插入或获取key视为被使用一次；而将已经存在的值替换更新，不算被使用。

限制：请在O(1)的时间复杂度内完成上述2个操作。
 */
public class LRUCacheTest {

    // 请特别注意“使用”的定义：新插入或获取key视为被使用一次；而将已经存在的值替换更新，不算被使用。
    // 只有 get 和 put 新值时相应的记录提到其那面
    // linkedHashMap put 新值时是移动的吗？
    public static void main(String[] args) {
        LRUCacheImpl2 lruCacheSimple =  new LRUCacheImpl2(2);
        lruCacheSimple.put(1, 1);
        lruCacheSimple.put(2, 2);
        System.out.println(lruCacheSimple.get(1));
        lruCacheSimple.put(2, 102); // 原声的更新时丢失了会删除
        lruCacheSimple.put(3, 3);
        System.out.println(lruCacheSimple.get(1));
        System.out.println(lruCacheSimple.get(2));
        System.out.println(lruCacheSimple.get(3));

    }

}
