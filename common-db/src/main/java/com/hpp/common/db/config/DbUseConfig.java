package com.hpp.common.db.config;

import com.hpp.common.db.basic.DataBase;

/**
 * Created by huangpingping on 2016/1/26 14:07
 * Db use by project config, provide params to init connections
 */
public class DbUseConfig extends DataBase {
    private String jdbcUrl;

    private String dbName;

    private String userName;

    private String password;

    public DbUseConfig(){

    }

    public DbUseConfig(String jdbcUrl){
        this.jdbcUrl = jdbcUrl;
    }

    public DbUseConfig(String jdbcUrl,String dbName,String userName,String password){
        this.jdbcUrl = jdbcUrl;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
