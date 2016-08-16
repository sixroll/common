package com.hpp.common.proxy;

import org.junit.Test;

/**
 * Created by huangpingping on 2016/8/15 10:56
 */
public class TestProxy {

    @Test
    public void testInvocation() {
        Runnable testRunnable = new TestRunnable();
        Runnable runnable = SubjectInvocationHandler.getProxyInstanceFactory(testRunnable);
        runnable.run();
    }

    @Test
    public void testInvocation1() {
        TestRunnable testRunnable = new TestRunnable();
        TestRunnable runnable = SubjectInvocationHandler.getProxyClassFactory(testRunnable);
        runnable.run();
    }



}
