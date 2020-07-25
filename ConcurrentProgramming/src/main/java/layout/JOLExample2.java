package layout;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * <h1>无锁的情况下，对象头 MarkWord 字段的 前 56 bit 存的是对象的 hashCode 验证</h1>
 */
public class JOLExample2 {

    public static void main(String[] args) throws Exception {
        A a = new A();

        System.out.println("Before hash: ");
        // 没有计算 hashCode 之前的对象头
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        // JAM 计算的 hashCode
        System.out.println("JVM cal hashCode for a: " + Integer.toHexString(a.hashCode()));

//        HashUtil.countHash(a);
        // 当计算完 hashCode 之后，我们查看对象头的信息变化
        System.out.println("After hash: ");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
