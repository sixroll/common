package com.hpp.common.db;

import com.hpp.common.db.basic.RowBuilder;
import com.hpp.common.db.transaction.Transaction;
import com.hpp.common.utils.IntegerUtils;
import com.hpp.common.db.basic.Builder;
import com.mchange.v1.db.sql.StatementUtils;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by huangpingping on 2016/1/27 13:59
 */
public abstract class AbstractSqlExecutor implements SqlExecutor {
    protected Connection connection;

    protected Transaction transaction;

    public AbstractSqlExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer executeUpdate(String sql, Object... params) throws SQLException {
        PreparedStatement statement = null;
        try {
            setAutoCommit();
            statement = connection.prepareStatement(sql);
            setPrepareStatementParams(statement, params);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            StatementUtils.attemptClose(statement);
            closeOnTransaction();
        }
    }

    @Override
    public Integer executeBatch(String sql, Iterable<Object[]> paramsArray) throws SQLException {
        PreparedStatement statement = null;
        try {
            setAutoCommit();
            statement = connection.prepareStatement(sql);
            for (Object[] params : paramsArray) {
                setPrepareStatementParams(statement, params);
                statement.addBatch();
            }
            return IntegerUtils.isAllMeanning(statement.executeBatch());
        } catch (SQLException e) {
            throw e;
        } finally {
            StatementUtils.attemptClose(statement);
            closeOnTransaction();
        }
    }


    private void setPrepareStatementParams(PreparedStatement statement, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        }
    }

    @Override
    public <RESULT> RESULT selectOne(String sql, RowBuilder<RESULT> rowBuilder, Object[] params) throws SQLException {
        List<RESULT> results = selectList(sql, rowBuilder, params);
        if (results == null) {
            return null;
        }
        for (RESULT result : results) {
            return result;
        }
        return null;
    }

    @Override
    public <RESULT> List<RESULT> selectList(String sql, RowBuilder<RESULT> rowBuilder, Object[] params) throws SQLException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            setPrepareStatementParams(statement, params);
            ResultSet rs = statement.executeQuery();
            return Builder.build(rowBuilder).apply(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    @Override
    public void close() throws SQLException {
        Assert.notNull(this.connection);
        if (!this.connection.isClosed()) {
            this.connection.close();
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.connection == null || this.connection.isClosed();
    }

    @Override
    public void commit() throws SQLException {
        Assert.notNull(this.connection);
        this.connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        Assert.notNull(this.connection);
        this.connection.rollback();
    }

    @Override
    public void bindTransaction(Transaction transaction) {
        this.transaction = transaction;
        if (transaction != null) {
            transaction.addSqlExecutor(this);
        }
    }

    @Override
    public Transaction transaction() {
        return this.transaction;
    }


    private void setAutoCommit() throws SQLException {
        this.connection.setAutoCommit(transaction() == null);
    }


    private void closeOnTransaction() throws SQLException {
        if (transaction == null) {
            this.connection.close();
        }
    }
}
