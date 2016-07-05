package com.hpp.common.db.transaction;

import com.hpp.common.db.Db;
import com.hpp.common.db.DbFactory;
import com.hpp.common.db.annotation.Transactional;
import com.hpp.common.db.config.DbUseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/7/4 12:04
 */
@Service
public class TestService {

    private final static String insertSql = "insert into tb_password(user_id,`type`,`password`) values(?,?,?)";


    private Db testDb;

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/test?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

    @Autowired
    private DbFactory dbFactory;


    public void init(){
        testDb = dbFactory.createDb(new DbUseConfig(jdbcUrl));
    }


    @Transactional
    public void testSingleConnectionRollback() throws SQLException {
        testDb.createExecutor().executeUpdate(insertSql, 1, 1, "1111111");

        testDb.createExecutor().executeUpdate(insertSql, 2, "wqe", "2222222");
    }


    @Transactional
    public void testCompletedTransaction() throws SQLException {
        testDb.createExecutor().executeUpdate(insertSql, 11, 11, "1111111");

        testDb.createExecutor().executeUpdate(insertSql, 22, 22, "2222222");
    }


    @Transactional
    public void testMuti() throws SQLException {
        testCompletedTransaction();
        testSingleConnectionRollback();
    }
}
