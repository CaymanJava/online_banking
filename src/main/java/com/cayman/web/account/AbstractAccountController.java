package com.cayman.web.account;


import com.cayman.entity.Account;
import com.cayman.service.AccountService;
import com.cayman.web.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public abstract class AbstractAccountController {
    @Autowired
    protected AccountService accountService;

    public Account get(int id) {
        int userId = LoggedUser.id();
        return accountService.get(userId, id);
    }

    public List<Account> getAll() {
        int userId = LoggedUser.id();
        return accountService.getAll(userId);
    }

    public Account create(Account account){
        int id = LoggedUser.id();
        return accountService.save(account, id);
    }

    public Account update(Account account) {
        int id = LoggedUser.id();
        return accountService.update(account, id);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        accountService.delete(userId, id);
    }
}
