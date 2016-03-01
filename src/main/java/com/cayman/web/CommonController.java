package com.cayman.web;


import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CommonController {
    public int getUserId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("userId"));
        return Integer.parseInt(id);
    }

    public int getAccountId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("accountId"));
        return Integer.parseInt(id);
    }
    public String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
