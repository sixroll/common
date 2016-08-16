package com.hpp.common.db;

import com.hpp.common.page.Page;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by huangpingping on 2016/8/15 10:34
 */
public class PageCglibProxy implements MethodInterceptor {

    private SqlDataExecutor sqlDataExecutor;// CGLib需要代理的目标对象


    public PageCglibProxy(SqlDataExecutor sqlDataExecutor) {
        this.sqlDataExecutor = sqlDataExecutor;
    }


    public static <F extends SqlDataExecutor, T extends F> T getProxyInstanceFactory(F realObj) {
        PageCglibProxy pageCglibProxy = new PageCglibProxy(realObj);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(realObj.getClass());
        enhancer.setCallback(pageCglibProxy);
        Object proxyObj = enhancer.create();
        return proxyObj == null ? null : (T) proxyObj;// 返回代理对象
    }

    private final static String METHOD_NAME = "selectList";

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before page intercept...");

        if (METHOD_NAME.equals(method.getName())) {
            Object[] sqlArgs = (Object[]) args[2];
            if (sqlArgs.length >= 1 && sqlArgs[sqlArgs.length - 1] instanceof Page) {
                System.out.println("in to page intercept...");
                args[2] = Arrays.copyOfRange(args, 0, sqlArgs.length - 1);
                return method.invoke(sqlDataExecutor, args);
            }
        }
        System.out.println("not step into page intercept...");
        return method.invoke(sqlDataExecutor, args);

    }
}
