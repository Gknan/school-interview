package atomic;

import lock.TestReturnFinally;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 演示原子数组的使用方法
 */
public class AtomicArrayDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray =
                new AtomicIntegerArray(1000);

        Runnable r1 = new Incrementer(atomicIntegerArray);
        Runnable r2 = new Derementer(atomicIntegerArray);
        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i] = new Thread(r2);
            threadsIncrementer[i] = new Thread(r1);

            threadsDecrementer[i].start();
            threadsIncrementer[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i].join();
            threadsIncrementer[i].join();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误" + i);
            }
        }
        System.out.println("运行结束");

    }
}

class Derementer implements Runnable{
    AtomicIntegerArray atomicIntegerArray;

    public Derementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable{
    AtomicIntegerArray atomicIntegerArray;

    public Incrementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndIncrement(i);
        }
    }
}

