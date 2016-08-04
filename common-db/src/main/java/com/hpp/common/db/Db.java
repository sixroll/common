package com.hpp.common.db;

import com.hpp.common.basic.Identification;
import com.hpp.common.db.basic.RowBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by huangpingping on 2016/1/25 11:10
 */
public interface Db extends Identification, SqlDataExecutor {
    SqlExecutor getExecutor() throws SQLException;

    @Override
    default Integer executeUpdate(String sql, Object... params) throws SQLException {
        return getExecutor().executeUpdate(sql, params);
    }

    @Override
    default Integer executeBatch(String sql, Iterable<Object[]> paramsArray) throws SQLException {
        return getExecutor().executeBatch(sql, paramsArray);
    }

    @Override
    default <RESULT> RESULT selectOne(String sql, RowBuilder<RESULT> rowBuilder, Object... params) throws SQLException {
        return getExecutor().selectOne(sql, rowBuilder, params);
    }

    @Override
    default <RESULT> List<RESULT> selectList(String sql, RowBuilder<RESULT> rowBuilder, Object... params) throws SQLException {
        return getExecutor().selectList(sql, rowBuilder, params);
    }
}
