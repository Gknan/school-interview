package abstractfactory;

/**
 * 具体工厂类，生产 AK 系列一套产品，区别于 工厂方法，只生产一类产品
 */
public class AK_Factory implements AbstractFactory {
    @Override
    public Gun produceGun() {
        return new AK();
    }

    @Override
    public Bullet produceBullet() {
        return new AK_Bullet();
    }
}
