package mustlearn;

import java.util.Comparator;

/**
 * <h1>LRU 接口定义</h1>
 */
public interface LRUCache {

    // 添加元素
    public void put(int key, int value);

    // 查看口元素
    public int get(int key);

    // 删除指定 key 的 value
    public int remove(int key);
}
