package com.hpp.common.db;

import com.hpp.common.db.config.DbUseConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by huangpingping on 2016/7/4 14:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/config/root-context.xml"})
public class TestDb {
    private Db testDb;

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/test?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

    @Autowired
    private DbFactory dbFactory;

    @Before
    public void before() throws SQLException {
        testDb = dbFactory.createDb(new DbUseConfig(jdbcUrl));
    }

    @Test
    public void testQuery() throws SQLException {
        Password password = new Password();

        List<Password> result = testDb.selectList("select * from tb_password limit ?", password.builder(), new Object[]{3});

        System.out.println(result);
    }



    @Test
    public void testPageQuery() throws SQLException {

    }
}
