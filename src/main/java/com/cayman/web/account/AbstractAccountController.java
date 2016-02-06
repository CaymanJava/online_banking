package com.cayman.web.account;


import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;
import com.cayman.service.AccountHistoryService;
import com.cayman.service.AccountService;
import com.cayman.web.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public abstract class AbstractAccountController {
    @Autowired
    protected AccountService accountService;

    @Autowired
    protected AccountHistoryService historyService;

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

    public void putMoney(int id, BigDecimal value) {
        int userId = LoggedUser.id();
        //accountService.putMoneyIntoAccount(userId, id, value);
        accountService.putMoneyFromOutside(userId, id, value);
    }

    public TransactionTransferObject getTransactionInformation(int id, String accountNumber, String comment, BigDecimal value) {
        int userId = LoggedUser.id();
        return accountService.getTransactionInformation(userId, id, comment, accountNumber, value);
    }

    public void sendMoney(int senderId, int senderAccountId,
                          int recipientId, int recipientAccountId,
                          Currency currency, String comment,
                          BigDecimal transferAmount, BigDecimal commission, BigDecimal amountForReceive) {
        accountService.sendMoney(
                senderId, senderAccountId,
                recipientId, recipientAccountId,
                currency, comment,
                transferAmount, commission, amountForReceive);
    }

    public List<AccountHistoryTransferObject> getAccountHistory(int accountId) {
        int userId = LoggedUser.id();
        return historyService.getAllHistoryByAccountId(userId, accountId);
    }

    public List<AccountHistoryTransferObject> getHistoryBetweenWithOption(LocalDate startDate, LocalTime startTime,
                                                                          LocalDate endDate, LocalTime endTime,
                                                                          int accountId, String option) {
        int userId = LoggedUser.id();
        return historyService.getHistoryBetweenWithOption(
                startDate, startTime,
                endDate, endTime,
                userId, accountId, option);
    }
}
