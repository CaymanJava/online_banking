package com.cayman.service;

import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.AccountHistory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public interface AccountHistoryService {
    void saveSendMoney(Account senderAccount, Account recipientAccount, Account commissionAccount,
                       String comment, BigDecimal senderAmount, BigDecimal commissionAmount, BigDecimal recipientAmount);
    void saveAddedMoney(Account recipientAccount, BigDecimal recipientAmount);
    List<AccountHistory> getAllHistoryByUserId(int userId);
    List<AccountHistoryTransferObject> getAllHistoryByAccountId(int userId, int accountId);
    AccountHistory getHistoryById(int accountHistoryId);
    List<AccountHistory> getHistoryBetweenByUserId(LocalDateTime start, LocalDateTime end, int userId);
    List<AccountHistoryTransferObject> getAllHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end,
                                                                       int userId,  int accountId);
    List<AccountHistoryTransferObject> getCreditHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end,
                                                                       int userId,  int accountId);
    List<AccountHistoryTransferObject> getDebitHistoryBetweenByAccountId(LocalDateTime start, LocalDateTime end,
                                                                       int userId,  int accountId);
    List<AccountHistoryTransferObject> getHistoryBetweenWithOption(LocalDate startDate, LocalTime startTime,
                                                                   LocalDate endDate, LocalTime endTime,
                                                                   int userId, int accountId,String option);
    List<AccountHistoryTransferObject> getCommissionHistoryBetween(LocalDate startDate, LocalTime startTime,
                                                                   LocalDate endDate, LocalTime endTime,
                                                                   int accountId);

}
