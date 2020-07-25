package ratelimiter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 演示 Guava BloomFilter
 */
public class BloomFilterDemo {

    public static void main(String[] args) {

        int size = 10000000;
        double fpp = 0.01;

        BloomFilter<Integer> filter =
                BloomFilter.create(Funnels.integerFunnel(), size, fpp);


        for (int i = 0; i < 10000; i++) {
            filter.put(i);
        }

        int errorCnt = 0;
        for (int i = 20000; i < 30000; i++) {
            if (filter.mightContain(i)) {
                errorCnt ++;
            }
        }

        System.out.println(errorCnt);
    }
}
