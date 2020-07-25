package mustlearn;

import java.util.LinkedHashMap;
import java.util.Map;

/*
继承 LinkedHashMap 实现 LRU Cache
Redis 部分还会问的
 */
public class LRUCacheSimple extends LinkedHashMap {

    // 容量
    private int CAPACITY;

    public LRUCacheSimple(int cap) {
        super((int)Math.ceil(cap / 0.75) + 1, 0.75f, true);
        CAPACITY = cap;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > CAPACITY;
    }
}
