package com.hpp.common.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huangpingping on 2016/8/15 11:52
 */
public class CGLibProxy implements MethodInterceptor {

    private Object targetObject;// CGLib需要代理的目标对象


    public CGLibProxy() {

    }

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;// 返回代理对象
    }

    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        checkPopedom();// 检查权限
        return method.invoke(targetObject, args);
    }

    private void checkPopedom() {
        System.out.println(".:检查权限  checkPopedom()!");
    }

}
