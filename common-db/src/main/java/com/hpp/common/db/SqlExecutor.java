package com.hpp.common.db;

import com.hpp.common.db.transaction.Transaction;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/25 11:13
 * All sql execute must by this interface
 */
public interface SqlExecutor extends SqlDataExecutor {

    void close() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    Transaction transaction();

    void bindTransaction(Transaction transaction);

    boolean isClosed() throws SQLException;
}
