package com.cayman.repository;

import com.cayman.entity.AccountHistory;

import java.time.LocalDateTime;
import java.util.List;


public interface AccountHistoryRepository {
    void saveInHistory(AccountHistory accountHistory);
    List<AccountHistory> getAllHistoryByUserId(int userId);
    List<AccountHistory> getAllHistoryByAccountId(int userId, int accountId);
    AccountHistory getHistoryById(int accountHistoryId);
    List<AccountHistory> getHistoryBetweenByUserId(LocalDateTime start, LocalDateTime end, int userId);
    List<AccountHistory> getAllHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId);
    List<AccountHistory> getCreditHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId);
    List<AccountHistory> getDebitHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId);
    List<AccountHistory> getCommissionHistoryBetween(LocalDateTime start, LocalDateTime end, int accountId);
}
