package com.hpp.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by huangpingping on 2016/1/27 10:50
 */
public class ClassUtils {
    public static Field[] getAllFields(Object obj) {
        return obj.getClass().getDeclaredFields();
    }

    public static void fillFieldValue(Object obj, Field field, Object value) {
        try {
            Method method = obj.getClass().getMethod(String.format("set%s", StringUtils.captureName(field.getName())), value.getClass());
            method.invoke(obj, value);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
