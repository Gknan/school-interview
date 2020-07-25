package cn.hust.interview.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * GCLib 动态代理类
 */
public class CGLibProxy implements MethodInterceptor {

    // 被动态代理的对象
    private Object targetObj;


    /**
     * 创建代理对象
     * @param obj 被代理对象
     * @return
     */
    public Object createProxyObject(Object obj) {
        this.targetObj = obj;

        // 使用 Enhancer.create 创建代理类 enhancer 是增强的意思
        Enhancer enhancer = new Enhancer();
        // 设置传入的类是代理类的父类
        enhancer.setSuperclass(obj.getClass());
        // 设置返回的代理代
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }


    /**
     * 拦截器，代理类的方法，并增强
     * @param o 被代理类
     * @param method 被代理的方法
     * @param args 参数
     * @param methodProxy 被代理的方法代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method,
                            Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("这里是对目标类的增强");

        // 调用被代理类的方法
        Object obj = method.invoke(targetObj, args);

        return obj;
    }
}
