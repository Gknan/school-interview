package concurrenttoolspractice.threadlocal;

/**
 * ThreadLocal 用法2，避免一个线程内传递参数的麻烦
 */
public class ThreadLocalNormUsage06 {

    public static void main(String[] args) {
        new Service1().process();
    }
}


class Service1 {
    public void process() {
        User user = new User("王哥");
        UserContextHolder.holder.set(user);
        // 一个 Thread 会用到很多不同的 ThreadLocal 对象
        ThreadSafeFormatter.dateFormatThreadLocal.get();
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2 拿到用户名" + user.name);
        UserContextHolder.holder.remove();
        User user2 = new User("李姐");
        UserContextHolder.holder.set(user2);
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3 拿到用户名" + user.name);

        // 避免内部泄露的发生
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {
    // 持有者
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}