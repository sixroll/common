package com.hpp.common.db.transaction;

import com.hpp.common.db.SqlExecutor;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/25 11:46
 */
public interface Transaction {
    void commit() throws SQLException;

    void rollBack() throws SQLException;

    void addSqlExecutor(SqlExecutor sqlExecutor);
}
