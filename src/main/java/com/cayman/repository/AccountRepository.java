package com.cayman.repository;

import com.cayman.entity.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AccountRepository {
    List<Account> getAll(int userId);
    Account get(int userId, int accountId);
    Account save(Account account);
    boolean delete(int userId, int accountId);
    boolean sendMoney(Account fromAccount, Account toAccount,
                      BigDecimal amountInSenderCurrency, BigDecimal amountInRecipientCurrency);

    Account getAccountByAccountNumber(String accountNumber);
    int getUserIdByAccountId(int accountId);
    boolean isAccountAvailable(int accountId);
    boolean isEnoughMoneyInAccount(int accountId);
}
