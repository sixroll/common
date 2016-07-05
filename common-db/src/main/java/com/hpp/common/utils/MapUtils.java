package com.hpp.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by huangpingping on 2015/11/27 16:54.
 */
public class MapUtils {
    /**
     * 非深拷贝
     *
     * @param sourceMap
     * @param keys
     * @return
     */
    public static <K, V> Map<K, V> getColsMap(Map<K, V> sourceMap, K... keys) {
        if (keys == null || keys.length == 0 || sourceMap == null) {
            return sourceMap;
        }
        Map<K, V> resultMap = new HashMap<>();
        for (K key : keys) {
            resultMap.put(key, sourceMap.get(key));
        }
        return resultMap;
    }



    public static <K, V> void removeKeys(Map<K, V> source, K... keys) {
        if (source == null || keys == null || keys.length == 0) {
            return;
        }
        for (K key : keys) {
            source.remove(key);
        }
    }


    public static Map toMap(String key, Object obj) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(key, obj);
        return resultMap;
    }

    public static <K, V, T extends V> T getValue(Map<K, V> map, K key) {
        V value = getValue(map, key, null);
        return value == null ? null : (T) value;
    }

    public static <K, V> V getValue(Map<K, V> map, K key, V defaultValue) {
        if (valueNULL(map, key)) {
            return defaultValue;
        } else {
            return map.get(key);
        }
    }


    public static <K, V> boolean valueNULL(Map<K, V> map, K key) {
        return (map == null || key == null || !map.containsKey(key) || map.get(key) == null);
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 感觉不是太好用的样子，先仍在这吧，emptyFillFunc这个参数比较蛋疼，暂时没想到好的方式
     *
     * @param map
     * @param key
     * @param value
     * @param emptyValueFunc
     * @param canSame
     * @param <K>
     * @param <V>
     */
    @Deprecated
    public static <K, V> void put(Map<K, Collection<V>> map, K key, V value, Supplier<Collection<V>> emptyValueFunc, boolean canSame) {
        if (map != null && key != null) {
            Collection<V> values = map.get(key);
            if (values == null) {
                values = emptyValueFunc.get();
                map.put(key, values);
            }
            if (canSame || !values.contains(value)) {
                values.add(value);
            }
        }
    }


    public static <K, V> V readMapCacheValue(Map<K, V> cacheMap, K key, Supplier<V> defaultReadFunc) {
        if (valueNULL(cacheMap, key)) {
            V value = defaultReadFunc.get();
            if (cacheMap == null) {
                cacheMap = new HashMap<>();
            }
            cacheMap.put(key, value);
            return value;
        }
        return cacheMap.get(key);
    }
}
