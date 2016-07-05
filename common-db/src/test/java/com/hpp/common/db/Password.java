package com.hpp.common.db;

import com.hpp.common.db.basic.DataBase;

public class Password extends DataBase<Password> {
    private Integer id;

    private Integer userId;

    private Integer type;

    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return String.format("Id[%s],UserId[%s],Type[%s],Password[%s]", id, userId, type, password);
    }
}