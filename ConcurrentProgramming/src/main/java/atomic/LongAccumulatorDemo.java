package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * 演示LongAccumulator 用法
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        // 可以并行计算
        LongAccumulator accumulator = new LongAccumulator(
                (x, y) -> x * y, 1
        );

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        IntStream.range(1, 10).forEach(
                i -> executorService.submit(() -> accumulator.accumulate(i))
        );
//        accumulator.accumulate(1);
//        accumulator.accumulate(4);
        executorService.shutdown();
        while (!executorService.isTerminated()){}
        System.out.println(accumulator.getThenReset());
    }
}
