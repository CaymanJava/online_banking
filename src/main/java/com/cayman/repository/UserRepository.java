package com.cayman.repository;

import com.cayman.entity.User;

import java.util.List;



public interface UserRepository {
    User getUser(int userId);
    User saveUser(User user);
    boolean deleteUser(int userId);
    List<User> getAll();
    User getByLogin(String email);
}
