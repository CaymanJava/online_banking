package com.cayman.repository;

import com.cayman.entity.AccountHistory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class AccountHistoryRepositoryImpl implements AccountHistoryRepository {
    @Override
    public AccountHistory saveInHistory(AccountHistory accountHistory) {
        return null;
    }

    @Override
    public Collection<AccountHistory> getAllHistoryByUserId(int userId) {
        return null;
    }

    @Override
    public Collection<AccountHistory> getAllHistoryByAccountId(int accountId) {
        return null;
    }

    @Override
    public Collection<AccountHistory> getAllHistory() {
        return null;
    }

    @Override
    public AccountHistory getHistoryById(int accountHistoryId) {
        return null;
    }

    @Override
    public Collection<AccountHistory> getHistoryBetween(LocalDateTime start, LocalDateTime end, int userId) {
        return null;
    }

    @Override
    public Collection<AccountHistory> getHistoryBetween(LocalDateTime start, LocalDateTime end) {
        return null;
    }
}
