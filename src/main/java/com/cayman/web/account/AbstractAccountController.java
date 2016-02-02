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

    public List<Account> getAll() {
        int userId = LoggedUser.id();
        return accountService.getAll(userId);
    }


}
