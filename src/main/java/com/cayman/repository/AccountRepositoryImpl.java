package com.cayman.repository;

import com.cayman.entity.Account;
import com.cayman.entity.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> getAll(int userId) {
        return entityManager.createNamedQuery(Account.GET_ALL, Account.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Account get(int userId, int accountId) {
        List<Account> accountList = entityManager.createNamedQuery(Account.GET, Account.class)
                .setParameter("accountId", accountId)
                .setParameter("userId",userId)
                .getResultList();
        return DataAccessUtils.singleResult(accountList);
    }

    @Override
    public Account save(Account account, int userId) {
        User user = entityManager.getReference(User.class, userId);
        account.setUser(user);
        if (account.isNew()) {
            entityManager.persist(account);
            return account;
        } else {
            return entityManager.merge(account);
        }
    }

    @Override
    public boolean delete(int userId, int accountId) {
        return entityManager.createNamedQuery(Account.DELETE)
                .setParameter("userId", userId)
                .setParameter("accountId", accountId)
                .executeUpdate() != 0;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return entityManager.createNamedQuery(Account.GET_BY_NUMBER, Account.class)
                .setParameter("accountNumber", accountNumber)
                .getSingleResult();
    }

    @Override
    public Account getAccountByDefaultNumberAndUserId(int userId) {
        List<Account> accountList = entityManager.createNamedQuery(Account.GET_BY_NUMBER_AND_USER_ID, Account.class)
                .setParameter("userId", userId)
                .setParameter("accountNumber", Account.DEFAULT_ACCOUNT_NUMBER)
                .getResultList();
        return DataAccessUtils.singleResult(accountList);
    }
}