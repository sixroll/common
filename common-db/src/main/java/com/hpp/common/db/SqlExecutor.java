package com.hpp.common.db;

import com.hpp.common.db.transaction.Transaction;
import com.hpp.common.db.basic.RowBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by huangpingping on 2016/1/25 11:13
 * All sql execute must by this interface
 */
public interface SqlExecutor {
    Integer executeUpdate(String sql, Object... params) throws SQLException;

    Integer executeBatch(String sql, Iterable<Object[]> paramsArray) throws SQLException;

    <RESULT> RESULT selectOne(String sql, RowBuilder<RESULT> rowBuilder, Object... params) throws SQLException;

    <RESULT> List<RESULT> selectList(String sql, RowBuilder<RESULT> rowBuilder, Object... params) throws SQLException;

    void close() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    Transaction transaction();

    void bindTransaction(Transaction transaction);

    boolean isClosed() throws SQLException;
}
