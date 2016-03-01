package com.cayman.web.account;


import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;
import com.cayman.service.AccountService;
import com.cayman.service.UserService;
import com.cayman.util.exceptions.ExceptionUtils;
import com.cayman.web.CommonController;
import com.cayman.web.LoggedUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public abstract class AbstractAccountController extends CommonController {
    protected final static Logger LOG = Logger.getLogger(AbstractAccountController.class);

    @Autowired
    protected AccountService accountService;

    public Account get(int id) {
        LOG.info("get account " + id);
        int userId = LoggedUser.id();
        return accountService.get(userId, id);
    }

    public List<Account> getAll() {
        LOG.info("get all");
        int userId = LoggedUser.id();
        return accountService.getAll(userId);
    }

    public Account create(Account account){
        int id = LoggedUser.id();
        LOG.info("create account for user id=" + id);
        return accountService.save(account, id);
    }

    public Account update(Account account) {
        int id = LoggedUser.id();
        LOG.info("update account for user id=" + id);
        return accountService.update(account, id);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete account id=" + id + " userId=" + userId );
        accountService.delete(userId, id);
    }

    public void putMoney(int id, BigDecimal value) {
        int userId = LoggedUser.id();
        LOG.info("put " + value + " into account id=" + id);
        accountService.putMoneyFromOutside(userId, id, value);
    }

    public TransactionTransferObject getTransactionInformation(int id, String accountNumber, String comment, BigDecimal value) {
        LOG.info("get transaction information");
        int userId = LoggedUser.id();
        return accountService.getTransactionInformation(userId, id, comment, accountNumber, value);
    }

    public void sendMoney(int senderId, int senderAccountId,
                          int recipientId, int recipientAccountId,
                          Currency currency, String comment,
                          BigDecimal transferAmount, BigDecimal commission, BigDecimal amountForReceive) {
        LOG.info("send money from user id=" + senderId + " to id=" + recipientId + ". " +
                "From account id=" + senderAccountId + " to id=" + recipientAccountId);
        accountService.sendMoney(
                senderId, senderAccountId,
                recipientId, recipientAccountId,
                currency, comment,
                transferAmount, commission, amountForReceive);
    }
}
