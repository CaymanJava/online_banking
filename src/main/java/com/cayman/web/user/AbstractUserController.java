package com.cayman.web.user;

import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.AccountHistory;
import com.cayman.entity.User;
import com.cayman.service.AccountHistoryService;
import com.cayman.service.AccountService;
import com.cayman.service.UserService;
import com.cayman.web.account.AbstractAccountController;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public abstract class AbstractUserController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected AccountHistoryService historyService;

    public User get(int userId) {
        return userService.get(userId);
    }

    public User create(User user) {
        return userService.save(user);
    }

    public void update(User user) {
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

    public List<Account> getUsersAccount(int userId) {
        return accountService.getAll(userId);
    }

    public List<AccountHistoryTransferObject> getAccountHistoryForAdmin(int userId, int accountId) {
        return historyService.getAllHistoryByAccountId(userId, accountId);
    }

    public List<AccountHistoryTransferObject> getHistoryBetweenWithOptionForAdmin(
            LocalDate startDate, LocalTime startTime,
            LocalDate endDate, LocalTime endTime,
            int accountId, int userId, String option) {
        return historyService.getHistoryBetweenWithOption(startDate, startTime, endDate, endTime, userId, accountId, option);
    }

    public void blockAccount(int userId, int accountId) {
        Account account = accountService.get(userId, accountId);
        account.setEnable(false);
        accountService.update(account, userId);
    }

    public void unBlockAccount(int userId, int accountId) {
        Account account = accountService.get(userId, accountId);
        account.setEnable(true);
        accountService.update(account, userId);
    }

    public List<AccountHistoryTransferObject> getHistoryCommissionBetween(LocalDate startDate, LocalTime startTime,
                                                                          LocalDate endDate, LocalTime endTime,
                                                                          int accountId) {
        return historyService.getCommissionHistoryBetween(startDate, startTime, endDate, endTime, accountId);
    }
}
