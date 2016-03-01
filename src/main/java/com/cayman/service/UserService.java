package com.cayman.service;

import com.cayman.entity.User;

import java.util.List;


public interface UserService {
    User get(int userId);
    User save(User user);
    void update(User user);
    void delete(int userId);
    List<User> getAll();
    User getByLogin(String email);
}
