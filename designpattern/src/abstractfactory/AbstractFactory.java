package abstractfactory;

/**
 * 抽象工厂接口
 * 提供生产一套产品的方法
 */
public interface AbstractFactory {

    public Gun produceGun();
    public Bullet produceBullet();

}
