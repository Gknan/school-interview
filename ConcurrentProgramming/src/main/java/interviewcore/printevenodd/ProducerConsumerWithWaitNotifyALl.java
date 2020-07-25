package interviewcore.printevenodd;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 使用 wait notify notifyAll 实现生产者消费者模式
 *
 * 首先，生产者，消费者两个对象；由于两者的速度不匹配，所以需要解耦，一般使用缓存区来解耦
 * 这里可以使用 wait notify 来解耦
 * 使用 一个 容器作为仓库，
 * 对于生产者而言，当队列满是，阻塞生产者，不满时，唤醒自己生产
 * 对消费者而言，当仓库空时，阻塞自己，不空时，开始消费
 *
 * 使用一个 StoryEvent 来解耦
 * 给生产者掺入引用，StoryEvent 提供 put take 方法
 *
 * 这里的同步使用 synchronized 加上方法上，其实就是加在了类 this 上，注意的是 synchronized 是加在通向资源上
 * 我们是给共享资源加锁的
 *
 *
 */
public class ProducerConsumerWithWaitNotifyALl {

    public static void main(String[] args) {
        int maxSize = 10;
        StorageEvent storage = new StorageEvent(maxSize);
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {

    StorageEvent storage;

    public Producer(StorageEvent storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        // 只负责生产
        for (int i = 0; i < 100; i++) {
            System.out.println("生产者生产第" + i + "个商品");
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    StorageEvent storage;

    public Consumer(StorageEvent storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        // 只负责消费
        for (int i = 0; i < 100; i++) {
            System.out.println("消费者消费第" + i + "个商品");
            storage.take();
        }
    }
}

class StorageEvent {
    int maxSize;
    LinkedList storage;

    public StorageEvent(int maxSize) {
        this.maxSize = maxSize;
        this.storage = new LinkedList<Date>();
    }

    public synchronized void put() {
        while (storage.size() == maxSize) {
            // 满了 阻塞自己 释放锁
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 若对列不满，可以添加元素
        storage.add(new Date());
        // 唤醒当前等待的所有元素
        notifyAll();
    }

    public synchronized void take() {
        while (storage.size() == 0) {
            try {
                // 当前队列为空，阻塞自己，释放锁
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 当前队列不空 消费 唤醒其他
        storage.poll();
        notifyAll();
    }
}
