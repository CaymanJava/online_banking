package com.cayman.web.user;

import com.cayman.entity.Account;
import com.cayman.entity.Message;
import com.cayman.entity.Role;
import com.cayman.entity.User;
import com.cayman.service.AccountService;
import com.cayman.service.MessageService;
import com.cayman.service.UserService;

import com.cayman.util.UserUtil;
import com.cayman.util.exceptions.DoNotTouchSuperAdminException;
import com.cayman.util.exceptions.ExceptionUtils;
import com.cayman.web.CommonController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public abstract class AbstractUserController extends CommonController{
    protected final static Logger LOG = Logger.getLogger(AbstractUserController.class);

    @Autowired
    protected UserService userService;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected MessageService messageService;

    public User get(int userId) {
        LOG.info("get " + userId);
        return userService.get(userId);
    }

    public User create(HttpServletRequest request) {
        User user = userService.save(UserUtil.prepareToSave(createUserByDefaultFields(request)));
        LOG.info("save " + user);
        messageService.sendToUser(new Message(Message.WELCOME_SUBJECT, Message.WELCOME_TEXT), user.getId());
        return user;
    }

    public void update(HttpServletRequest request) {
        int userId = getUserId(request);
        LOG.info("update " + userId);
        User editUser = createUserByDefaultFields(request);
        editUser.setId(userId);
        editUser.setEnabled(request.getParameter("enabled").equals("true"));
        editUser.setRole(Role.valueOf(request.getParameter("role")));
        try {
            editUser.setRegistered(new SimpleDateFormat().parse(request.getParameter("registered")));
        } catch (ParseException ignored){}
        userService.update(UserUtil.prepareToSave(editUser));
    }

    public void delete(int userId) {
        LOG.info("delete " + userId);
        ExceptionUtils.checkSuperAdmin(get(userId));
        userService.delete(userId);
    }

    public List<User> getAll(){
        LOG.info("getAll");
        return userService.getAll();
    }

    public List<Account> getUsersAccounts(int userId) {
        LOG.info("get all users account " + userId);
        return accountService.getAll(userId);
    }

    public void changeAccountStatus(int userId, int accountId) {
        LOG.info("change account status. userId=" + userId + " accountId=" + accountId);
        Account account = accountService.get(userId, accountId);
        if (account.isEnable()) {
            account.setEnable(false);
        } else {
            account.setEnable(true);
        }
        accountService.update(account, userId);
    }

    public void changeUserStatus(int userId) {
        LOG.info("change user status id=" + userId);
        User user = get(userId);
        if (user.isEnabled()) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
        userService.update(user);
    }

    public void changeUserRole(int userId) {
        LOG.info("change user role id=" + userId);
        User user = get(userId);
        if (user.getRole().name().equals("ROLE_USER")) {
            user.setRole(Role.ROLE_ADMIN);
        } else {
            user.setRole(Role.ROLE_USER);
        }
        userService.update(user);
    }

    public User createUserByDefaultFields(HttpServletRequest request){
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email").toLowerCase();

        return new User(null, login, password, firstName, lastName, email);
    }
}
