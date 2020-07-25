package layout;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/*
查看 java 对象头结构
 */
public class JOLExample1 {

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }
}
