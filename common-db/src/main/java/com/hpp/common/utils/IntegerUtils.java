package com.hpp.common.utils;

/**
 * Created by huangpingping on 2016/1/25 16:20
 */
public class IntegerUtils {
    public static Integer isAllMeanning(int... values) {
        if(values == null || values.length == 0) {
            return 0;
        } else {
            for(int value : values) {
                if(value == 0){
                    return 0;
                }
            }
            return 1;
        }
    }
}
