package com.cayman.repository;

import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;

import java.math.BigDecimal;
import java.util.List;


public interface AccountRepository {
    List<Account> getAll(int userId);
    Account get(int userId, int accountId);
    Account save(Account account, int userId);
    boolean delete(int userId, int accountId);
    Account getAccountByAccountNumber(String accountNumber);
    Account getAccountByDefaultNumberAndUserId(int userId);
}
