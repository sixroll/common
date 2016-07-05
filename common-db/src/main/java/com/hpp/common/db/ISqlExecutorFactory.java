package com.hpp.common.db;

import com.hpp.common.db.transaction.Transaction;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/7/4 13:37
 */
public interface ISqlExecutorFactory{
    SqlExecutor createSqlExecutor(Db db) throws SQLException;


    void bindTransaction(Transaction transaction);
}
