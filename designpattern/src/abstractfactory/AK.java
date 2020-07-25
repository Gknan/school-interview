package abstractfactory;

/**
 * 抽象工厂模式：抽象产品1 的具体实现类
 */
public class AK implements Gun {
    @Override
    public void shooting() {
        System.out.println("shooting with AK");
    }
}
