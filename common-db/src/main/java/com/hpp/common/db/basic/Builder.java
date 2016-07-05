package com.hpp.common.db.basic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Builder<T> implements Function<ResultSet, List<T>> {
    private RowBuilder<T> rowBuilder;

    public Builder(RowBuilder<T> rowBuilder) {
        this.rowBuilder = rowBuilder;
    }


    public static <T> Builder<T> build(RowBuilder<T> rowBuilder) {
        return new Builder<>(rowBuilder);
    }

    @Override
    public List<T> apply(ResultSet rs) {
        List<T> result = new ArrayList<>();
        try {
            while(rs.next()) {
                result.add(rowBuilder.apply(rs));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}