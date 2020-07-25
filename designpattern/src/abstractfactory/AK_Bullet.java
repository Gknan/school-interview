package abstractfactory;

/**
 * 抽象产品2 具体实现类
 */
public class AK_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with AK");
    }
}
