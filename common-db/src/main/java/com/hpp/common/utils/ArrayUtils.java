package com.hpp.common.utils;

/**
 * Created by huangpingping on 2016/8/5 11:11
 */
public class ArrayUtils {
    public static <T> boolean isEmpty(T[] ts) {
        return ts == null || ts.length == 0;
    }


    public static <T> T lastElement(T[] ts) {
        if(isEmpty(ts)){
            return null;
        }
        return ts[ts.length - 1];
    }
}
