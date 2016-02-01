package com.cayman.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//TODO create index
@Table(name = "account_histories", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"from_account_id","to_account_id"}, name = "histories_userId_accountId_idx")}
)
public class AccountHistory {
    @Id
    @SequenceGenerator(name = "account_history_seq", sequenceName = "account_history_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_history_seq")
    private int operationId;

    @Column(name = "from_user_id", nullable = false)
    @NotEmpty
    private int fromUserId;

    @Column(name = "to_user_id", nullable = false)
    @NotEmpty
    private int toUserId;

    @Column(name = "from_account_id", nullable = false)
    @NotEmpty
    private int fromAccountId;

    @Column(name = "to_account_id", nullable = false)
    @NotEmpty
    private int toAccountId;

    @Column(name = "operation_time", columnDefinition = "timestamp default now()")
    private LocalDateTime operationTime;

    @Column(name = "sender_currency", nullable = false)
    @NotEmpty
    private Currency senderCurrency;

    @Column(name = "recipient_currency", nullable = false)
    @NotEmpty
    private Currency recipientCurrency;

    @Column(name = "credit", nullable = false)
    @NotEmpty
    private boolean credit = true;

    @Column(name = "from_account_number", nullable = false)
    @NotEmpty
    private String fromAccountNumber;

    @Column(name = "to_account_number", nullable = false)
    @NotEmpty
    private String toAccountNumber;

    @Column(name = "operation_amount", nullable = false)
    @NotEmpty
    private BigDecimal operationAmount;

    @Column(name = "commission", nullable = false)
    @NotEmpty
    private BigDecimal commission;

    @Column(name = "amount_before_operation_on_sender", nullable = false)
    @NotEmpty
    private BigDecimal amountBeforeOperationOnSender;

    @Column(name = "amount_after_operation_on_sender", nullable = false)
    @NotEmpty
    private BigDecimal amountAfterOperationOnSender;

    @Column(name = "amount_before_operation_on_recipient", nullable = false)
    @NotEmpty
    private BigDecimal amountBeforeOperationOnRecipient;

    @Column(name = "amount_after_operation_in_recipient", nullable = false)
    @NotEmpty
    private BigDecimal amountAfterOperationInRecipient;

    public AccountHistory(){
    }

    public AccountHistory(int operationId,
                          int fromUserId, int toUserId,
                          int fromAccountId, int toAccountId,
                          LocalDateTime operationTime,
                          Currency senderCurrency,
                          Currency recipientCurrency,
                          boolean credit,
                          String fromAccountNumber, String toAccountNumber,
                          BigDecimal operationAmount,
                          BigDecimal commission,
                          BigDecimal amountBeforeOperationOnSender, BigDecimal amountAfterOperationOnSender,
                          BigDecimal amountBeforeOperationOnRecipient, BigDecimal amountAfterOperationInRecipient) {
        this.operationId = operationId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.operationTime = operationTime;
        this.senderCurrency = senderCurrency;
        this.recipientCurrency = recipientCurrency;
        this.credit = credit;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.operationAmount = operationAmount;
        this.commission = commission;
        this.amountBeforeOperationOnSender = amountBeforeOperationOnSender;
        this.amountAfterOperationOnSender = amountAfterOperationOnSender;
        this.amountBeforeOperationOnRecipient = amountBeforeOperationOnRecipient;
        this.amountAfterOperationInRecipient = amountAfterOperationInRecipient;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public Currency getSenderCurrency() {
        return senderCurrency;
    }

    public void setSenderCurrency(Currency senderCurrency) {
        this.senderCurrency = senderCurrency;
    }

    public Currency getRecipientCurrency() {
        return recipientCurrency;
    }

    public void setRecipientCurrency(Currency recipientCurrency) {
        this.recipientCurrency = recipientCurrency;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(BigDecimal operationAmount) {
        this.operationAmount = operationAmount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getAmountBeforeOperationOnSender() {
        return amountBeforeOperationOnSender;
    }

    public void setAmountBeforeOperationOnSender(BigDecimal amountBeforeOperationOnSender) {
        this.amountBeforeOperationOnSender = amountBeforeOperationOnSender;
    }

    public BigDecimal getAmountAfterOperationOnSender() {
        return amountAfterOperationOnSender;
    }

    public void setAmountAfterOperationOnSender(BigDecimal amountAfterOperationOnSender) {
        this.amountAfterOperationOnSender = amountAfterOperationOnSender;
    }

    public BigDecimal getAmountBeforeOperationOnRecipient() {
        return amountBeforeOperationOnRecipient;
    }

    public void setAmountBeforeOperationOnRecipient(BigDecimal amountBeforeOperationOnRecipient) {
        this.amountBeforeOperationOnRecipient = amountBeforeOperationOnRecipient;
    }

    public BigDecimal getAmountAfterOperationInRecipient() {
        return amountAfterOperationInRecipient;
    }

    public void setAmountAfterOperationInRecipient(BigDecimal amountAfterOperationInRecipient) {
        this.amountAfterOperationInRecipient = amountAfterOperationInRecipient;
    }
}