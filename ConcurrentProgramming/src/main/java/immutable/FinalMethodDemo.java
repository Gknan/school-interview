package immutable;

/**
 * final 修饰方法
 */
public class FinalMethodDemo {

//    public final FinalMethodDemo() {
//
//    }

    public static void sleep() {

    }



    public void drink() {

    }

    public final void eat() {

    }
}

class SubClass extends FinalMethodDemo {

    @Override
    public void drink() {
        super.drink();
        eat();
    }

//    public void eat() {
//
//    }

    public static void sleep() {

    }
}
