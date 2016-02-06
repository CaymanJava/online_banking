package com.cayman.dto;


import com.cayman.entity.Account;
import com.cayman.entity.Currency;

import java.math.BigDecimal;

public class TransactionTransferObject {
    private Account senderAccount;
    private Account recipientAccount;
    private BigDecimal amount;
    private BigDecimal commission;
    private BigDecimal amountInRecipientCurrency;
    private String comment;

    public TransactionTransferObject(Account senderAccount, Account recipientAccount, String comment,
                                     BigDecimal amount, BigDecimal commission, BigDecimal amountInRecipientCurrency) {
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        this.comment = comment;
        this.amount = amount;
        this.commission = commission;
        this.amountInRecipientCurrency = amountInRecipientCurrency;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getAmountInRecipientCurrency() {
        return amountInRecipientCurrency;
    }

    public void setAmountInRecipientCurrency(BigDecimal amountInRecipientCurrency) {
        this.amountInRecipientCurrency = amountInRecipientCurrency;
    }

    public Currency getSenderCurrency(){
        return senderAccount.getCurrency();
    }

    public Currency getRecipientCurrency(){
        return recipientAccount.getCurrency();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
