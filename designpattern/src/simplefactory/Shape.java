package simplefactory;

/**
 * 简单工厂模式中的工厂创建的产品的父接口，定义了产品的公共特性
 * 这里的简单工厂 是一个创建各种图形的工厂 包括 圆形，方形等
 */
public interface Shape {

    void draw();
}
