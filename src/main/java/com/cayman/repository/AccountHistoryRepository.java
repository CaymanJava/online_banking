package com.cayman.repository;

import com.cayman.entity.AccountHistory;

import java.time.LocalDateTime;
import java.util.Collection;



public interface AccountHistoryRepository {
    AccountHistory saveInHistory(AccountHistory accountHistory);
    Collection<AccountHistory> getAllHistoryByUserId(int userId);
    Collection<AccountHistory> getAllHistoryByAccountId(int accountId);
    Collection<AccountHistory> getAllHistory();
    AccountHistory getHistroyById(int accountHistoryId);
    Collection<AccountHistory> getHistoryBetween(LocalDateTime start, LocalDateTime end, int userId);
    Collection<AccountHistory> getHistoryBetween(LocalDateTime start, LocalDateTime end);
}
