package com.hpp.common.db.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/25 16:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/config/root-context.xml"})
public class TestRollback {
    @Autowired
    private TestService testService;

    @Before
    public void doBefore(){
        testService.init();
    }


    @Test
    public void test() throws SQLException {
        testService.testCompletedTransaction();

        testService.testCompletedTransaction();

        testService.testMuti();

        testService.testSingleConnectionRollback();
    }

}
