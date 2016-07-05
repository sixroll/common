package com.hpp.common.db.basic;

import java.sql.ResultSet;
import java.util.function.Function;

/**
 * Created by huangpingping on 2016/1/25 11:23
 */
public interface RowBuilder<RESULT> extends Function<ResultSet, RESULT> {

}
