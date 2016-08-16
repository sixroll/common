package com.hpp.common.db;

import com.hpp.common.db.config.DbUseConfig;
import com.hpp.common.db.connection.C3P0ConnectionManager;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/7/4 16:56
 */
public class C3P0Db implements Db {
    private C3P0ConnectionManager c3P0ConnectionManager;
    private DbUseConfig dbUseConfig;

    private ThreadLocal<SqlExecutor> sqlExecutorThreadLocal = new ThreadLocal<>();

    protected C3P0Db(DbUseConfig dbUseConfig) {
        this.dbUseConfig = dbUseConfig;
        c3P0ConnectionManager = new C3P0ConnectionManager(dbUseConfig);
    }

    @Override
    public SqlExecutor getExecutor() throws SQLException {
        SqlExecutor sqlExecutor = sqlExecutorThreadLocal.get();
        if(sqlExecutor == null || sqlExecutor.isClosed()){
            DefaultSqlExecutor newExecutor = new DefaultSqlExecutor();
            newExecutor.setConnection(c3P0ConnectionManager.getConnection());
            sqlExecutorThreadLocal.set(newExecutor);
            return newExecutor;
        }
        return sqlExecutor;
    }

    @Override
    public String identity() {
        return dbUseConfig.toString();
    }
}
