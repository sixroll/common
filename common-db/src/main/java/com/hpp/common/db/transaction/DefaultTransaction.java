package com.hpp.common.db.transaction;

import com.hpp.common.db.SqlExecutor;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/25 16:50
 */
public class DefaultTransaction implements Transaction {
    private ExecutorContainer executorContainer;

    public DefaultTransaction() {
        this.executorContainer = new ExecutorContainer();
    }

    @Override
    public void commit() throws SQLException {
        try {
            for (SqlExecutor sqlExecutor : executorContainer.getExecutors()) {
                sqlExecutor.commit();
            }
        } catch (Exception e) {
            rollBack();
            throw new SQLException(e);
        } finally {
            for (SqlExecutor sqlExecutor : executorContainer.getExecutors()) {
                sqlExecutor.close();
            }
        }
    }

    @Override
    public void rollBack() throws SQLException {
        for (SqlExecutor sqlExecutor : executorContainer.getExecutors()) {
            sqlExecutor.rollback();
        }
    }

    @Override
    public void addSqlExecutor(SqlExecutor sqlExecutor) {
        this.executorContainer.add(sqlExecutor);
    }
}
