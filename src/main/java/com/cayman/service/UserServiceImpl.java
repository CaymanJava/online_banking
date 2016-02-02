package com.cayman.service;


import com.cayman.entity.User;
import com.cayman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User get(int userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public void update(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public void delete(int userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
