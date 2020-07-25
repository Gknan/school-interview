package factorymethod;

import simplefactory.Shape;

/**
 * 抽象工厂接口，提供所有具体工厂接口的公共方法
 */
public interface Factory {

    public Shape getShape();
}
