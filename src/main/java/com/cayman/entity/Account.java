package com.cayman.entity;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
//TODO create index for DB
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "", name = "")})
public class Account {
    //public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private int accountId;

    @Column(name = "name", nullable = true)
    @NotEmpty
    private String name;

    @Column(name = "account_number", nullable = false)
    @NotEmpty
    private String accountNumber;

    @Column(name = "currency", nullable = false)
    @NotEmpty
    private Currency currency;

    @Column(name = "amount", nullable = false)
    @NotEmpty
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account(int accountId, String name, String accountNumber, Currency currency, BigDecimal amount) {
        this.accountId = accountId;
        this.name = name;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
    }

    public Account(){
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
