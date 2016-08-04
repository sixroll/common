package com.hpp.common.db;

import com.hpp.common.db.basic.RowBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by huangpingping on 2016/8/4 11:38
 */
public interface SqlDataExecutor {
    Integer executeUpdate(String sql, Object... params) throws SQLException;

    Integer executeBatch(String sql, Iterable<Object[]> paramsArray) throws SQLException;

    <RESULT> RESULT selectOne(String sql, RowBuilder<RESULT> rowBuilder, Object... params) throws SQLException;

    <RESULT> List<RESULT> selectList(String sql, RowBuilder<RESULT> rowBuilder, Object... params) throws SQLException;
}
