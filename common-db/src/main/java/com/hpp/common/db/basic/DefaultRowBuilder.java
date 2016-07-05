package com.hpp.common.db.basic;

import com.hpp.common.db.annotation.Ignore;
import com.hpp.common.utils.ClassUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/1/27 11:24
 */
public class DefaultRowBuilder<RESULT> implements RowBuilder<RESULT> {

    private Class<RESULT> resultClass;

    public DefaultRowBuilder(Class<RESULT> resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public RESULT apply(ResultSet rs) {
        try {
            RESULT result = resultClass.newInstance();
            Field[] fields = ClassUtils.getAllFields(result);
            for(Field field : fields) {
                if(field.getAnnotation(Ignore.class) == null) {
                    fillValue(result, field, rs);
                }
            }
            return result;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * TODO table-field's name and type mapping is here
     * 表字段 名以及值类型映射 就在这个方法中修改
     */
    private void fillValue(RESULT result, Field field, ResultSet rs) throws SQLException {
        ClassUtils.fillFieldValue(result, field, rs.getObject(field.getName()));
    }
}
