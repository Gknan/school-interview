package jmm;

import singleton.Singleton8;

/**
 * <h1>演示可见性带来的问题</h1>
 */
public class FieldVisibility {

    int a = 1;
    int abc = 0;
    int abcd = 999;
    volatile int b = 2;

    public static void main(String[] args) {

        while (true) {
            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    test.print();
                }
            }).start();
//            Singleton8.INSTANCE.whatever();
//            System.out.println(Singleton8.INSTANCE);
        }
    }

    private void change() {
        a = 3;
        abc = -222;
        abcd = 90;
        // b 用 volatile 修饰，根据 h-b 原则，在读取 b 之前，b 之前的所有操作都是可见的，所以
        // b 的可见性保证了 a 的可见性
        b = 0;
    }

    private void print() {
        // b 起到了触发器的作用
        if (b == 0) {
            System.out.println("a=" + a + ";abc="+abc+";abcd="+abcd);
        }
    }
}
