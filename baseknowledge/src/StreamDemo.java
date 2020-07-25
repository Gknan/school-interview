import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 的用法
 */

public class StreamDemo {

    public static void main(String[] args) {
//        List<String> strings = Arrays.asList(
//                "abc", "", "bc", "efg", "abcd", "", "jki"
//        );

//        // 使用 filter
//        List<String> collect = strings.stream()
//                .filter(string -> !string.isEmpty())
//                .collect(Collectors.toList());
//        System.out.println(collect);
//
//        // 使用 foreach 迭代流中的每个数据
//        Random random = new Random();
//        random.ints().limit(10).forEach(System.out::println);

        // map 用户映射每个元素到对应的结果，下面使用 map 输出了元素对应的平方数
//        List<Integer> numbers = Arrays.asList(2, 3, 1, 4, 3, 7, 9);
//        // 获取对应的平方数
//        List<Integer> squareList = numbers
//                .stream().map(i -> i * i)
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(squareList);
//
//        // limit 用户获取制定数量的流
//        Random random = new Random();
//        random.ints().limit(10).forEach(System.out::println);

//        // sorted 用于对流进行排序，下面对 10 个随机数的流排序
//        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);

        // 统计 主要用于 int double long 等基本类型
//        List<Integer> numbers = Arrays.asList(4, 1, 2, 4, 5, 6, 2);
//        IntSummaryStatistics statistics = numbers.stream().mapToInt((x) -> x).summaryStatistics();
//        System.out.println(statistics.getMax());
//        System.out.println(statistics.getMin());
//        System.out.println(statistics.getSum());
//        System.out.println(statistics.getAverage());

        // Collectors 将流转换成 集合和 聚合元素；Collectors 可用于返回列表或字符串
        List<String> strs = Arrays.asList(
                "abc", "", "bc", "efg", "abcd", "", "jkl"
        );
        List<String> collect = strs.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        System.out.println(collect);
        String mergedStr = strs.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("-"));
        System.out.println(collect);
        System.out.println(mergedStr);
    }
}
