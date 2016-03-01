package com.cayman.service;

import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.AccountHistory;
import com.cayman.repository.AccountHistoryRepository;
import com.cayman.util.AccountHistoryTransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountHistoryServiceImpl implements AccountHistoryService{

    @Autowired
    private AccountHistoryRepository historyRepository;

    @Override
    @Transactional
    public void saveSendMoney(Account senderAccount, Account recipientAccount, Account commissionAccount,
                              String comment,
                              BigDecimal senderAmount, BigDecimal commissionAmount, BigDecimal recipientAmount) {
        AccountHistory accountHistory = new AccountHistory(
                senderAccount.getUser().getId(), recipientAccount.getUser().getId(),
                senderAccount.getId(), recipientAccount.getId(),
                commissionAccount.getId(),
                senderAccount.getCurrency(), recipientAccount.getCurrency(),
                senderAccount.getAccountNumber(), recipientAccount.getAccountNumber(),
                comment,
                senderAmount, recipientAmount, commissionAmount,
                senderAccount.getBalance(),
                recipientAccount.getBalance());
        historyRepository.saveInHistory(accountHistory);
    }

    @Override
    @Transactional
    public void saveAddedMoney(Account recipientAccount, BigDecimal recipientAmount) {
        AccountHistory accountHistory = new AccountHistory(
                recipientAccount.getUser().getId(), recipientAccount.getId(),
                recipientAccount.getCurrency(), recipientAccount.getAccountNumber(),
                recipientAmount, recipientAccount.getBalance());
        historyRepository.saveInHistory(accountHistory);
    }

    //for admin only
    @Override
    public List<AccountHistory> getAllHistoryByUserId(int userId) {
        return historyRepository.getAllHistoryByUserId(userId);
    }

    @Override
    public List<AccountHistoryTransferObject> getAllHistoryByAccountId(int userId, int accountId) {
        return AccountHistoryTransferUtil.getDTOListFromHistoryList(
                historyRepository.getAllHistoryByAccountId(userId, accountId), accountId);
    }

    @Override
    public AccountHistory getHistoryById(int accountHistoryId) {
        return historyRepository.getHistoryById(accountHistoryId);
    }

    @Override
    public List<AccountHistory> getHistoryBetweenByUserId(LocalDateTime start, LocalDateTime end, int userId) {
        return historyRepository.getHistoryBetweenByUserId(start, end, userId);
    }

    @Override
    public List<AccountHistoryTransferObject> getAllHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end,
                                                                              int userId, int accountId) {
        return AccountHistoryTransferUtil.getDTOListFromHistoryList(
                historyRepository.getAllHistoryBetweenByAccountId(start, end, userId, accountId), accountId);
    }

    @Override
    public List<AccountHistoryTransferObject> getCreditHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId) {
        return AccountHistoryTransferUtil.getDTOListFromHistoryList(
                historyRepository.getCreditHistoryBetweenByAccountId(start, end, userId, accountId), accountId);
    }

    @Override
    public List<AccountHistoryTransferObject> getDebitHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end, int userId, int accountId) {
        return AccountHistoryTransferUtil.getDTOListFromHistoryList(
                historyRepository.getDebitHistoryBetweenByAccountId(start, end, userId, accountId), accountId);
    }

    @Override
    public List<AccountHistoryTransferObject> getHistoryBetweenWithOption(LocalDate startDate, LocalTime startTime,
                                                                          LocalDate endDate, LocalTime endTime,
                                                                          int userId, int accountId, String option) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        switch (option) {
            case "credit": {
                return getCreditHistoryBetweenByAccountId(startDateTime, endDateTime, userId, accountId);
            }
            case "debit": {
                return getDebitHistoryBetweenByAccountId(startDateTime, endDateTime, userId, accountId);
            }
            default: {
                return getAllHistoryBetweenByAccountId(startDateTime, endDateTime, userId, accountId);
            }
        }
    }

    @Override
    public List<AccountHistoryTransferObject> getCommissionHistoryBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int accountId) {
        return AccountHistoryTransferUtil.getDTOCommissionList(
                historyRepository.getCommissionHistoryBetween(
                        LocalDateTime.of(startDate, startTime),
                        LocalDateTime.of(endDate, endTime),
                        accountId));
    }
}
