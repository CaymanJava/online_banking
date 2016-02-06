package com.cayman.service;


import com.cayman.entity.Account;
import com.cayman.entity.User;
import com.cayman.repository.AccountRepository;
import com.cayman.repository.UserRepository;
import com.cayman.util.exceptions.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public User get(int userId) {
        return userRepository.getUser(userId);
    }

    @Override
    /*@Transactional*/
    public User save(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    /*@Transactional*/
    public void update(User user) {
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void delete(int userId) {
        accountRepository.getAll(userId).forEach(ExceptionUtils::checkForZeroBalance);
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
