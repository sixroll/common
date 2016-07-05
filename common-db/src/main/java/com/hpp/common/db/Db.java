package com.hpp.common.db;

import com.hpp.common.basic.Identification;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/25 11:10
 */
public interface Db extends Identification{
    // TODO: 2016/7/5 可以考虑隐藏掉createExecutor这一层，而直接实现Executor的接口 ，外部调用能更方便
    SqlExecutor createExecutor() throws SQLException;
}
