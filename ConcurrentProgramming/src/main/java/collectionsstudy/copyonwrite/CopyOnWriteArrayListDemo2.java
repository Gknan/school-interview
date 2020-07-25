package collectionsstudy.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 演示 CopyOnWriteArrayList 迭代可以修改
 */
public class CopyOnWriteArrayListDemo2 {

    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});

        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();
        list.add(4);

        System.out.println(list);

        Iterator<Integer> iterator1 = list.iterator();

        iterator.forEachRemaining(System.out::println);
        iterator1.forEachRemaining(System.out::println);
    }
}
