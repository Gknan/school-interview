package collectionsstudy;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurentHashMapDmeo {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(1, 2);
        map.remove(3);
    }

}
