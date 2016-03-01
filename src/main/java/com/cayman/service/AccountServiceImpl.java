package com.cayman.service;

import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;
import com.cayman.repository.AccountRepository;
import com.cayman.util.AccountUtil;
import com.cayman.util.CurrencyConverter;
import com.cayman.util.exceptions.ExceptionUtils;
import com.cayman.util.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHistoryService historyService;

    @Override
    public List<Account> getAll(int userId) {
        return accountRepository.getAll(userId);
    }

    @Override
    public Account get(int userId, int accountId) {
        return accountRepository.get(userId, accountId);
    }

    @Override
    @Transactional
    public Account save(Account account, int userId) {
        Account createdAccount = accountRepository.save(account, userId);
        if (createdAccount.getAccountNumber().equals(Account.DEFAULT_ACCOUNT_NUMBER)) {
            createdAccount = createAndSaveNumberForNewAccount(userId);
        }
        return createdAccount;
    }

    @Override
    @Transactional
    public Account update(Account account, int userId) {
        return accountRepository.save(account, userId);
    }

    @Override
    @Transactional
    public boolean delete(int userId, int accountId) {
        ExceptionUtils.checkForZeroBalance(get(userId, accountId));
        return accountRepository.delete(userId, accountId);
    }

    @Override
    @Transactional
    public void sendMoney(int senderUserId, int senderAccountId,
                          int recipientUserId, int recipientAccountId,
                          Currency senderCurrency, String comment,
                          BigDecimal amountSender, BigDecimal commission, BigDecimal amountRecipient) {
        //withdraw money from sender account
        Account senderAccount = withdrawMoneyFromAccount(senderUserId, senderAccountId, amountSender);

        //pay commission
        Account commissionAccount = payCommission(commission, senderCurrency);

        //put money to recipient account
        Account recipientAccount = putMoneyIntoAccount(recipientUserId, recipientAccountId, amountRecipient);

        historyService.saveSendMoney(senderAccount, recipientAccount, commissionAccount,
                comment,  amountSender, commission, amountRecipient);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public void checkingAccountExistence(String accountNumber) {
        try {
             getAccountByAccountNumber(accountNumber);
        } catch(NoResultException e) {
            throw new NotFoundEntityException();
        }

    }

    @Override
    public void checkingAccountBalance(int userId, int accountId, BigDecimal amount) {
         ExceptionUtils.checkBalance(get(userId, accountId).getBalance(), amount);
    }

    @Override
    @Transactional
    public Account putMoneyIntoAccount(int userId, int accountId, BigDecimal amount) {
        Account account = get(userId, accountId);
        ExceptionUtils.checkAccountForBlocking(account);
        account.setBalance(AccountUtil.addMoney(account.getBalance(), amount));
        return save(account, userId);
    }


    @Override
    @Transactional
    public Account putMoneyFromOutside(int userId, int accountId, BigDecimal amount) {
        ExceptionUtils.checkForNegativeAndZeroAmount(amount);
        Account account = putMoneyIntoAccount(userId, accountId, amount);
        historyService.saveAddedMoney(account,amount);
        return account;
    }

    @Override
    public Account withdrawMoneyFromAccount(int userId, int accountId, BigDecimal amount) {
        Account account = get(userId, accountId);
        account.setBalance(AccountUtil.withdrawMoney(account.getBalance(), amount));
        return save(account, userId);
    }

    @Override
    public Account payCommission(BigDecimal commissionAmount, Currency currency) {
        Account commissionAccount = getAccountByAccountNumber(AccountUtil.getAccountNumberForCommission(currency));
        return putMoneyIntoAccount(commissionAccount.getUser().getId(), commissionAccount.getId(), commissionAmount);
    }

    @Override
    public TransactionTransferObject getTransactionInformation(int userId, int accountId, String comment,
                                                               String recipientAccountNumber, BigDecimal amount) {
        checkingAccountExistence(recipientAccountNumber);
        checkingAccountBalance(userId, accountId, amount);

        Account sender = get(userId, accountId);
        Account recipient = getAccountByAccountNumber(recipientAccountNumber);

        ExceptionUtils.checkAccountForBlocking(recipient);

        Currency senderCurrency = sender.getCurrency();
        Currency recipientCurrency = recipient.getCurrency();

        List<BigDecimal> commissionAndRecipientAmount = AccountUtil.countCommissionRate(amount);
        BigDecimal recipientAmount = commissionAndRecipientAmount.get(0);
        BigDecimal commission = commissionAndRecipientAmount.get(1);

        if (!senderCurrency.equals(recipientCurrency)) {
            recipientAmount = CurrencyConverter.convertMoney(senderCurrency, recipientCurrency, recipientAmount);
        }
        return new TransactionTransferObject(sender, recipient, comment, amount, commission, recipientAmount);
    }

    @Override
    public Account createAndSaveNumberForNewAccount(int userId) {
        Account account = accountRepository.getAccountByDefaultNumberAndUserId(userId);
        account.setAccountNumber(AccountUtil.createAccountNumber(userId, account.getId(), account.getCurrency()));
        return accountRepository.save(account, userId);
    }
}
