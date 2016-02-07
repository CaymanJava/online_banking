package com.cayman.repository;

import com.cayman.entity.AccountHistory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
/*@Transactional(readOnly = true)*/
public class AccountHistoryRepositoryImpl implements AccountHistoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    /*@Transactional*/
    public void saveInHistory(AccountHistory accountHistory) {
        entityManager.persist(accountHistory);
    }

    //for admin only
    @Override
    public List<AccountHistory> getAllHistoryByUserId(int userId) {
        return entityManager.createNamedQuery(AccountHistory.GET_ALL_BY_USER_ID, AccountHistory.class)
                .setParameter("fromUserId", userId)
                .setParameter("toUserId", userId)
                .getResultList();
    }

    @Override
    public List<AccountHistory> getAllHistoryByAccountId(int userId, int accountId) {
        return entityManager.createNamedQuery(AccountHistory.GET_ALL_BY_ACCOUNT_ID, AccountHistory.class)
                .setParameter("fromAccountId", accountId)
                .setParameter("toAccountId", accountId)
               /* .setParameter("commissionAccountId", accountId)*/
                .setParameter("fromUserId", userId)
                .setParameter("toUserId", userId)
                .getResultList();
    }

    //может вообще нахер не нужен этот метод
    @Override
    public AccountHistory getHistoryById(int accountHistoryId) {
        List<AccountHistory> resultList = entityManager.createNamedQuery(AccountHistory.GET_BY_ID, AccountHistory.class)
                .setParameter("id", accountHistoryId)
                .getResultList();
        return DataAccessUtils.singleResult(resultList);
    }

    // for admin only, а может и нет, может вообще не нужен
    @Override
    public List<AccountHistory> getHistoryBetweenByUserId(LocalDateTime start, LocalDateTime end, int userId) {
        return entityManager.createNamedQuery(AccountHistory.GET_BETWEEN_BY_USER_ID, AccountHistory.class)
                .setParameter("fromUserId", userId)
                .setParameter("toUserId", userId)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }

    @Override
    public List<AccountHistory> getAllHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId) {
        return entityManager.createNamedQuery(AccountHistory.GET_ALL_BETWEEN_BY_ACCOUNT_ID, AccountHistory.class)
                .setParameter("fromAccountId", accountId)
                .setParameter("toAccountId", accountId)
                .setParameter("fromUserId", userId)
                .setParameter("toUserId", userId)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }

    @Override
    public List<AccountHistory> getCreditHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId) {
        return entityManager.createNamedQuery(AccountHistory.GET_CREDIT_BETWEEN_BY_ACCOUNT_ID, AccountHistory.class)
                .setParameter("fromAccountId", accountId)
                .setParameter("fromUserId", userId)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }

    @Override
    public List<AccountHistory> getDebitHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId) {
        return entityManager.createNamedQuery(AccountHistory.GET_DEBIT_BETWEEN_BY_ACCOUNT_ID, AccountHistory.class)
                .setParameter("toAccountId", accountId)
                .setParameter("toUserId", userId)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }

    @Override
    public List<AccountHistory> getCommissionHistoryBetween(LocalDateTime start, LocalDateTime end, int accountId) {
        return entityManager.createNamedQuery(AccountHistory.GET_COMMISSION_HISTORY, AccountHistory.class)
                .setParameter("commissionAccountId", accountId)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }
}
