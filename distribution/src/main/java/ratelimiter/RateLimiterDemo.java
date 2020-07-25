package ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Arrays;
import java.util.List;

public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.5);

        List<Integer> list = Arrays.asList(1, 6, 2);
        for (Integer integer : list) {
            // 预先占 生成 integer 个令牌的时间
//            System.out.println(System.currentTimeMillis() + ": acq " + integer + " wait: " + rateLimiter.acquire(integer));
            System.out.println(rateLimiter.tryAcquire());
        }
    }
}
