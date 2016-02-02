package com.cayman.service;

import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;
import com.cayman.repository.AccountHistoryRepository;
import com.cayman.repository.AccountRepository;
import com.cayman.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountHistoryRepository historyRepository;

    @Override
    public List<Account> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Account get(int userId, int accountId) {
        return repository.get(userId, accountId);
    }

    @Override
    public Account save(Account account, int userId) {
        return repository.save(account, userId);
    }

    @Override
    public Account update(Account account, int userId) {
        return repository.save(account, userId);
    }

    @Override
    public boolean delete(int userId, int accountId) {
        return repository.delete(userId, accountId);
    }
    //TODO don't forget about adding history at this method


    @Override
    public void sendMoney(int senderUserId, int senderAccountId,
                          int recipientUserId, int recipientAccountId,
                          Currency senderCurrency,
                          BigDecimal amountSender, BigDecimal commission, BigDecimal amountRecipient) {
        //withdraw money from sender account
        withdrawMoneyFromAccount(senderUserId, senderAccountId, amountRecipient);

        //pay commission
        payCommission(commission, senderCurrency);

        //put money to recipient account
        putMoneyIntoAccount(recipientUserId, recipientAccountId, amountRecipient);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return repository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public boolean isAccountAvailable(String accountNumber) {
        return getAccountByAccountNumber(accountNumber).isEnable();
    }

    @Override
    public boolean isEnoughMoneyInAccount(int userId, int accountId, BigDecimal amount) {
        return AccountUtil.checkBalance(get(userId, accountId).getBalance(), amount);
    }

    @Override
    public void putMoneyIntoAccount(int userId, int accountId, BigDecimal amount) {
        Account account = get(userId, accountId);
        account.setBalance(AccountUtil.addMoney(account.getBalance(), amount));
        save(account, userId);
    }

    //TODO don't forget about adding history at this method
    @Override
    public void putMoneyFromOutside(int userId, int accountId, BigDecimal amount) {
        putMoneyIntoAccount(userId, accountId, amount);
    }

    @Override
    public void withdrawMoneyFromAccount(int userId, int accountId, BigDecimal amount) {
        Account account = get(userId, accountId);
        account.setBalance(AccountUtil.withdrawMoney(account.getBalance(), amount));
        save(account, userId);
    }

    @Override
    public void payCommission(BigDecimal commissionAmount, Currency currency) {
        Account commissionAccount = getAccountByAccountNumber(AccountUtil.getAccountNumberForCommission(currency));
        putMoneyIntoAccount(commissionAccount.getUser().getId(), commissionAccount.getId(), commissionAmount);
    }

    //TODO don't forget about this method
    @Override
    public TransactionTransferObject getTransactionInformation(int userId, int accountId, String recipientAccountNumber, BigDecimal amount) {
        return null;
    }
}
