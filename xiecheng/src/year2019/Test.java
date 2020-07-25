package year2019;

public class Test {

    public static Test t1=new Test(); // 没有初始化完成，依然是可以创建实例的，以为这时候
    // 可以使用准备阶段为每个变量赋的初始值来构造

    {
        System.out.println("blockA");
    }

    static {
        System.out.println("blockB");
    }

    public static void main(String[] args){
        Test t2 = new Test();

        System.out.println(t2.testException());
    }

    public int testException() {
        int i = 0;

        try {
            i ++;
            throw new Exception("hhhh");
        } catch (Exception e) {
            return i++;


        } finally {
            i ++;

            System.out.println("finally" + i);
        }

    }


}