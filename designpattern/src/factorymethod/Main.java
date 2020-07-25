package factorymethod;

import simplefactory.Shape;

/**
 * 测试工厂方法模式
 * 工厂方法模式使用最多，在工厂方法模式中，我们不再提供一个统一的工厂类来创建
 * 所有的对象，而是针对不同的对象提供不同的工厂， 每个对象都有一个与之对应的工厂
 *
 */
public class Main {

    public static void main(String[] args) {
        Factory circleFactory = new CircleFactory();
        Shape circle = circleFactory.getShape();
        circle.draw();
    }
}
