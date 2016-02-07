package com.cayman.web;


import com.cayman.entity.BaseEntity;

public class LoggedUser {
    public final static Integer ADMIN_ID = 1;

    private static int userId = BaseEntity.START_SEQ;

    public static int id() {
        return userId;
    }

    public static void setId(int id) {
        LoggedUser.userId = id;
    }
}
