package com.hpp.common.db.basic;

import com.hpp.common.db.annotation.Ignore;
import com.hpp.common.utils.ClassUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by huangpingping on 2016/7/4 15:01
 */
public class IdongriRuleBuilder<RESULT> implements RowBuilder<RESULT> {
    private Class<RESULT> resultClass;

    public IdongriRuleBuilder(Class<RESULT> resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public RESULT apply(ResultSet rs) {
        try {
            RESULT result = resultClass.newInstance();
            Field[] fields = ClassUtils.getAllFields(result);
            for (Field field : fields) {
                if (field.getAnnotation(Ignore.class) == null) {
                    fillValue(result, field, rs);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void fillValue(RESULT result, Field field, ResultSet rs) throws SQLException {
        String dbFieldName = changeAttrToDatabase(field.getName());
        ClassUtils.fillFieldValue(result, field, rs.getObject(dbFieldName));
    }


    public String changeAttrToDatabase(String attr) {
        StringBuffer newAttr = new StringBuffer();

        for (char c : attr.toCharArray()) {
            if (Character.isUpperCase(c)) {
                newAttr.append("_" + Character.toLowerCase(c));
            } else {
                newAttr.append(c);
            }
        }
        return newAttr.toString();
    }
}
