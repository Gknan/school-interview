package cn.hust.interview.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理类
 */
public class JDKProxy implements InvocationHandler {

    // 需要代理的目标对象
    private Object targetObj;

    /**
     * 创建代理对象
     * @param targetObj 传入被代理的对象
     */
    public Object newProxy(Object targetObj) {
        this.targetObj = targetObj;
        // 返回 代理对象
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(),
                this);
    }

    /**
     * invoke 方法 调用类调用的方法
     * @param proxy 代理对象
     * @param method 增强的方法
     * @param args 增强方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 代理方法执行前的增强逻辑
        System.out.println("JDK代理增强前置");

        // 调用被代理类的方法
        Object invoke = method.invoke(targetObj, args);

        return invoke;


        // 代理方法执行后的增强逻辑
//        return null;
    }
}
