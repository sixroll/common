package com.hpp.common.db.basic;

/**
 * Created by huangpingping on 2016/1/26 17:39
 */
public interface IDataBase<T> {
    RowBuilder<T> builder();
}
