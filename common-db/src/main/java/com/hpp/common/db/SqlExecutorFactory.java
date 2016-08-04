package com.hpp.common.db;

import com.hpp.common.db.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/7/4 12:14
 */
@Component
public class SqlExecutorFactory implements ISqlExecutorFactory {

    private ThreadLocal<Transaction> transactionThreadLocal = new ThreadLocal<>();

    @Override
    public SqlExecutor createSqlExecutor(Db db) throws SQLException {
        SqlExecutor currentExecutor = db.getExecutor();
        currentExecutor.bindTransaction(transactionThreadLocal.get());

        return currentExecutor;
    }

    @Override
    public void bindTransaction(Transaction transaction) {
        transactionThreadLocal.set(transaction);
    }
}
