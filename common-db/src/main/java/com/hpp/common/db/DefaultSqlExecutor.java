package com.hpp.common.db;

import java.sql.Connection;

/**
 * Created by huangpingping on 2016/1/25 16:04
 */
public class DefaultSqlExecutor extends AbstractSqlExecutor {
    public DefaultSqlExecutor(){
        super(null);
    }

    public DefaultSqlExecutor(Connection connection) {
        super(connection);
    }


    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
