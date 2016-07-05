package com.hpp.common.db.connection;

import com.hpp.common.db.config.DbUseConfig;
import com.hpp.common.utils.MapUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.util.Assert;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangpingping on 2016/1/15 13:57
 */
public class C3P0ConnectionManager {

    private ComboPooledDataSource cpds;

    private C3P0ConnectionManager() {
        cpds = new ComboPooledDataSource(true);
        initDefaultValue();
    }

    public C3P0ConnectionManager(DbUseConfig dbUseConfig) {
        this();
        init(dbUseConfig);
    }


    public C3P0ConnectionManager(String jdbcUrl) {
        this(new DbUseConfig(jdbcUrl));
    }


    static class ParamInvoker<K, V> {
        public Map<K, V> container;

        public ParamInvoker() {
            container = new HashMap<>();
        }

        public <T extends V> T getValue(K key) {
            V value = MapUtils.getValue(container, key);
            return value == null ? null : (T) value;
        }

        public void put(K key, V value) {
            container.put(key, value);
        }

        public static ParamInvoker parse(String url) {
            ParamInvoker<String, Object> paramInvoker = new ParamInvoker<>();
            String[] fieldValues = url.split("&");

            for (String fieldValue : fieldValues) {
                String[] keyValue = fieldValue.split("=");
                paramInvoker.put(keyValue[0], keyValue[1]);
            }
            return paramInvoker;
        }
    }

    public C3P0ConnectionManager(String url, String userName, String password) {
        this(new DbUseConfig(url, null, userName, password));
    }


    private void initConfig(String url, String db, String userName, String password) {
        cpds.setJdbcUrl(url);

        if (db == null) {
            int endIndex = url.lastIndexOf("?") == -1 ? url.length() : url.lastIndexOf("?");
            db = url.substring(url.lastIndexOf("/"), endIndex);
        }
        cpds.setDataSourceName(db);

        cpds.setUser(userName);
        cpds.setPassword(password);
    }

    private void init(DbUseConfig dbUseConfig) {
        String jdbcUrl = dbUseConfig.getJdbcUrl();

        String[] jdbcValues = jdbcUrl.split("\\?");
        jdbcUrl = jdbcValues[0];

        ParamInvoker<String, Object> paramInvoker = ParamInvoker.parse(jdbcValues[1]);

        String user = dbUseConfig.getUserName() == null ? paramInvoker.getValue("user") : dbUseConfig.getUserName();

        String password = dbUseConfig.getPassword() == null ? paramInvoker.getValue("password") : dbUseConfig.getPassword();

        initConfig(jdbcUrl, null, user, password);
    }


    public Connection getConnection() {
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    void initDefaultValue() {
        Assert.notNull(cpds);
        try {
            cpds.setDriverClass("c3p0.driverClass");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setMaxPoolSize(5);
        cpds.setMinPoolSize(2);
        cpds.setAcquireIncrement(3);
        cpds.setInitialPoolSize(3);
        cpds.setMaxIdleTime(30);

        cpds.setTestConnectionOnCheckin(true);
        cpds.setIdleConnectionTestPeriod(30);
    }

}
