package com.cayman.repository;

import com.cayman.entity.AccountHistory;

import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Created by macuser on 31.01.16.
 */
public interface AccountHistoryRepository {
    AccountHistory save(AccountHistory accountHistory);
    Collection<AccountHistory> getAllByUserId(int userId);
    Collection<AccountHistory> getAllByAccountId(int accountId);
    Collection<AccountHistory> getAll();
    AccountHistory getById(int accountHistoryId);
    Collection<AccountHistory> getBetween(LocalDateTime start, LocalDateTime end, int userId);
    Collection<AccountHistory> getBetween(LocalDateTime start, LocalDateTime end);
}
