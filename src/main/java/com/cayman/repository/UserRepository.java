package com.cayman.repository;

import com.cayman.entity.User;

import java.util.List;


/**
 * Created by macuser on 31.01.16.
 */
public interface UserRepository {
    User getUser(int userId);
    User saveUser(User user);
    boolean deleteUser(int userId);
    List<User> getAll();
    User getByEmail(String email);
}
