package com.cayman.service;

import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;

import java.math.BigDecimal;
import java.util.List;


public interface AccountService {
    List<Account> getAll(int userId);
    Account get(int userId, int accountId);
    Account save(Account account, int userId);
    Account update(Account account, int userId);
    boolean delete(int userId, int accountId);
    void sendMoney(int senderUserId, int senderAccountId,
                   int recipientUserId, int recipientAccountId,
                   Currency senderCurrency, String comment,
                   BigDecimal amountSender, BigDecimal commission, BigDecimal amountRecipient);
    Account getAccountByAccountNumber(String accountNumber);
    void checkingAccountExistence(String accountNumber);
    void checkingAccountBalance(int userId, int accountId, BigDecimal amount);
    Account putMoneyIntoAccount(int userId, int accountId, BigDecimal amount);
    Account putMoneyFromOutside(int userId, int accountId, BigDecimal amount);
    Account withdrawMoneyFromAccount(int userId, int accountId, BigDecimal amount);
    Account payCommission(BigDecimal commissionAmount, Currency currency);
    TransactionTransferObject getTransactionInformation(int userId, int accountId, String comment,
                                                        String recipientAccountNumber, BigDecimal amount);
    Account createAndSaveNumberForNewAccount(int userId);
}
