package classloadandinit;

import java.util.Random;

/**
 * 演示接口的初始化时机
 */
public class InterfaceInit {
    public static void main(String[] args) {
        System.out.println(Child05.j);
    }
}

interface Father05 {
    int i = 5;
}

interface Child05 extends Father05 {
    int j = new Random().nextInt(8);

}