package com.cayman.web.user;

import com.cayman.entity.User;
import com.cayman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractUserController {

    @Autowired
    protected UserService userService;

    public User get(int userId) {
        return userService.get(userId);
    }

    public User create(User user) {
        return userService.save(user);
    }

    public void update(User user, int userId) {
        userService.update(user);
    }

    public void delete(int userId) {
        userService.delete(userId);
    }

    public List<User> getAll(){
        return userService.getAll();
    }

    public User getByEmail(String email) {
        return userService.getByEmail(email);
    }
}
