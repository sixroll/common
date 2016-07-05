package com.hpp.common.utils;

/**
 * Created by huangpingping on 2015/11/27 15:45.
 */
public class StringUtils {
    //首字母大写
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        if(cs[0] >= 'a' && cs[0] <= 'z') {
            cs[0] -= 32;
        }
        return String.valueOf(cs);
    }

    public static boolean isEmpty(String str) {
        if(str == null) {
            return true;
        }
        return "".equals(str.trim());
    }
}
