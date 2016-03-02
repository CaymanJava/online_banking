package com.cayman.service;


import com.cayman.entity.User;
import com.cayman.repository.AccountRepository;
import com.cayman.repository.UserRepository;
import com.cayman.util.exceptions.ExceptionUtils;
import com.cayman.web.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public User get(int userId) {
        return userRepository.getUser(userId);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void delete(int userId) {
        accountRepository.getAll(userId).forEach(ExceptionUtils::checkForZeroBalance);
        userRepository.deleteUser(userId);
    }

    @Override
    @Cacheable("users")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(login.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return new LoggedUser(user);
    }

}
