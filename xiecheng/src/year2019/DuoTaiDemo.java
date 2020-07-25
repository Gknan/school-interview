package year2019;

class Fu {

    int num = 5;

    static void method4() {

        System.out.println("fu method_4");

    }

    void method3() {

        System.out.println("fu method_3");

    }

}

class Zi extends Fu {

    int num = 8;

    static void method4() {

        System.out.println("zi method_4");

    }

    void method3() {

        System.out.println("zi method_3");

    }

}

public class DuoTaiDemo {

    public static void main(String[] args) {

        Fu f = new Zi();
        // 创建父类的引用 指向了子类对象，父类的和子类的同名方法被子类重写了
        // 父类的成员变量等还是父类的

        System.out.println(f.num);//与父类一致

        f.method4();//与父类一致

        f.method3();//编译时与父类一致，运行时与子类一致

        Zi z = new Zi();

        System.out.println(z.num);

        z.method4();

        z.method3();

    }

}