package com.hpp.common.db;

/**
 * Created by huangpingping on 2016/1/26 15:29
 */
public interface PartDbs {
    Db mapDb(Object key);

    Db[] getDbs();
}