package abstractfactory;

/**
 * 抽象工厂模式测试类
 * 抽象工厂是生产一整套产品的（至少生产两个产品），这些产品必须相互有关系或依赖的，
 * 而工厂方法中的工厂生产单一产品的工厂。
 *
 */
public class Main {

    public static void main(String[] args) {
        AbstractFactory ak_fac = new AK_Factory();
        Gun ak = ak_fac.produceGun();
        Bullet ak_bul = ak_fac.produceBullet();

        ak_bul.load();
        ak.shooting();

    }
}
