package com.hpp.common.db.basic;

import com.hpp.common.utils.MapUtils;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

/**
 * Created by huangpingping on 2016/1/26 17:37
 */
public abstract class DataBase<T extends DataBase> implements IDataBase<T> {
    static Map<String, RowBuilder> cacheMap = new WeakHashMap<>();

    final Supplier<RowBuilder> defaultRowBuild = () -> new IdongriRuleBuilder<>(this.getClass());

    public RowBuilder<T> builder() {
        return MapUtils.readMapCacheValue(cacheMap, this.getClass().getName(), defaultRowBuild);
    }
}
