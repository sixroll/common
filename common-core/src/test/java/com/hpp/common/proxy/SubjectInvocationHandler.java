package com.hpp.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by huangpingping on 2016/8/15 10:34
 */
public class SubjectInvocationHandler implements InvocationHandler {

    private Object obj;

    public SubjectInvocationHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * 生成代理类工厂
     */
    public static <T> T getProxyInstanceFactory(T realObj){
        Class<?> classType = realObj.getClass();
        Object resultObj =  Proxy.newProxyInstance(classType.getClassLoader(),
                classType.getInterfaces(), new SubjectInvocationHandler(realObj));
        return resultObj == null ? null : (T) resultObj;
    }


    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("before");

        Object object = method.invoke(obj, args);

        System.out.println("after");

        return object;
    }



    public static <T> T getProxyClassFactory(T realObj){
        CGLibProxy cgLibProxy = new CGLibProxy();
        T obj = (T) cgLibProxy.createProxyObject(realObj);
        return obj;
    }
}
