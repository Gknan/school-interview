package cn.hust.interview.aop;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        // 创建被代理类
        CircleShape circleShape = new CircleShape();
//
//        // 创建代理类
//        // 为什么可以接受呢，因为创建的动态代理对象实现了 Shape 接口
//        Shape proxy = (Shape) new JDKProxy().newProxy(circleShape);
//
//        // 调用增强后的方法
//        proxy.draw();

        // CGLib 动态代理测试
        CGLibProxy proxy2 = new CGLibProxy();
        CircleShape circleProxy = (CircleShape) proxy2.createProxyObject(circleShape);
        circleProxy.draw();

    }
}
