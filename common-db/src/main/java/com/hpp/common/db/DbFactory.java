package com.hpp.common.db;

import com.hpp.common.db.config.DbUseConfig;
import com.hpp.common.db.connection.C3P0ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/25 11:10
 */
@Component
public class DbFactory{
    @Autowired
    private ISqlExecutorFactory sqlExecutorFactory;

    void initConfig(String url, String userName, String password, String projectName) {
        C3P0ConnectionManager dbConfigSchema = new C3P0ConnectionManager(url, userName, password);
        DefaultSqlExecutor sqlExecutor = new DefaultSqlExecutor(dbConfigSchema.getConnection());
    }

    void initConfigFile(String filePath) {

    }


    public Db createDb(String dbName) {
        //TODO to completed!
        return null;
    }


    public PartDbs createPartDb(String partDbName) {
        //TODO to completed!
        return null;
    }

    public Db createDb(DbUseConfig dbUseConfig){
        Db db = new C3P0Db(dbUseConfig);
        return new ProxyDb(db);
    }


    class ProxyDb implements Db{
        private Db db;

        public ProxyDb(Db db){
            this.db = db;
        }

        @Override
        public SqlExecutor getExecutor() throws SQLException {
            SqlExecutor sqlExecutor = sqlExecutorFactory.createSqlExecutor(this.db);
            return sqlExecutor;
        }

        @Override
        public String identity() {
            return this.db.identity();
        }
    }
}
