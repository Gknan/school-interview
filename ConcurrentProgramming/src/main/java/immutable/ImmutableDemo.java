package immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 有个属性是对象，但是整体不可变，其他类无法修改 set 中的数据
 */
public class ImmutableDemo {

    private final Set<String> strings = new HashSet<>();

    public ImmutableDemo() {
        strings.add("wang萨那");

    }

    public boolean isStudent(String name) {
        return  strings.contains(name);
    }


}
