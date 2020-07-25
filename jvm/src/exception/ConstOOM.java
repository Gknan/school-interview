package exception;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * 模拟常量池 OOM
 * JDK 1.8 之后 常量池由原来的方法区迁移到现在的 堆中，所以应该报的是 OOM
 * 原来的方法区现在叫做 直接内存，也叫元空间，存在类相关的信息
 *
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class ConstOOM {

//    interface Student {
//        void study();
////        int age;
////        String name;
////
////        public Student(int age, String name) {
////            this.age = age;
////            this.name = name;
////        }
//    }

    public static void main(String[] args) {
//        ArrayList<Student> list = new ArrayList<>();
//        String STR = "Hello";

    }
}
