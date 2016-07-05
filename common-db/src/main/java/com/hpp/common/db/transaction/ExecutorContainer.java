package com.hpp.common.db.transaction;

import com.hpp.common.db.SqlExecutor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangpingping on 2016/1/25 16:52
 */
public class ExecutorContainer {
    List<SqlExecutor> sqlExecutors;

    public ExecutorContainer() {
        this.sqlExecutors = new ArrayList<>();
    }

    public List<SqlExecutor> getExecutors() {
        return this.sqlExecutors;
    }

    public void add(SqlExecutor sqlExecutor) {
        Assert.notNull(this.sqlExecutors);
        if (!this.sqlExecutors.contains(sqlExecutor)) {
            this.sqlExecutors.add(sqlExecutor);
        }
    }
}
