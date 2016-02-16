package com.cayman.dto;


import com.cayman.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class AccountHistoryTransferObject {
    private boolean debit;
    private LocalDateTime operationTime;
    private Currency currency;
    private String userAccountNumber;
    private String contractorAccountNumber;
    private String comment;
    private BigDecimal amount;
    private BigDecimal amountAfterOperation;
    private BigDecimal commission;
    private Integer accountId;

    public AccountHistoryTransferObject(boolean debit,
                                        LocalDateTime operationTime,
                                        Currency currency, String userAccountNumber, String contractorAccountNumber,
                                        String comment,
                                        BigDecimal amount, BigDecimal amountAfterOperation, BigDecimal commission,
                                        Integer accountId) {
        this.debit = debit;
        this.operationTime = operationTime;
        this.currency = currency;
        this.userAccountNumber = userAccountNumber;
        this.contractorAccountNumber = contractorAccountNumber;
        this.comment = comment;
        this.amount = amount;
        this.amountAfterOperation = amountAfterOperation;
        this.commission = commission;
        this.accountId = accountId;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getUserAccountNumber() {
        return userAccountNumber;
    }

    public void setUserAccountNumber(String userAccountNumber) {
        this.userAccountNumber = userAccountNumber;
    }

    public String getContractorAccountNumber() {
        return contractorAccountNumber;
    }

    public void setContractorAccountNumber(String contractorAccountNumber) {
        this.contractorAccountNumber = contractorAccountNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getAmountAfterOperation() {
        return amountAfterOperation;
    }

    public void setAmountAfterOperation(BigDecimal amountAfterOperation) {
        this.amountAfterOperation = amountAfterOperation;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
