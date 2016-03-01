package com.cayman.entity;


import com.cayman.util.AccountUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = Account.GET_ALL, query = "SELECT ac FROM Account ac WHERE ac.user.id = :userId ORDER BY ac.balance"),
        @NamedQuery(name = Account.GET, query = "SELECT ac FROM Account ac WHERE ac.id = :accountId AND ac.user.id = :userId"),
        @NamedQuery(name = Account.DELETE, query = "DELETE FROM Account ac WHERE ac.id = :accountId AND ac.user.id = :userId"),
        @NamedQuery(name = Account.GET_BY_NUMBER, query = "SELECT ac FROM Account ac WHERE ac.accountNumber = :accountNumber"),
        @NamedQuery(name = Account.GET_BY_NUMBER_AND_USER_ID, query = "SELECT ac FROM Account  ac WHERE ac.user.id = :userId " +
                "AND ac.accountNumber = :accountNumber"),
})

@Entity
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "account_number", name = "account_number_idx")})
public class Account extends BaseEntity {
    public final static Integer DEFAULT_ID = 0;
    public final static String EMPTY_STRING = " ";
    public final static String DEFAULT_ACCOUNT_NUMBER = "newAccountNumber";
    public final static String GET_ALL = "Account.getAll";
    public final static String GET = "Account.get";
    public final static String DELETE = "Account.delete";
    public final static String GET_BY_NUMBER = "Account.getByNumber";
    public final static String GET_BY_NUMBER_AND_USER_ID = "Account.getWithoutNumber";
    public final static BigDecimal ZERO_BALANCE = AccountUtil.createBigDecimal(0);

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "account_number")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    @NotNull
    private Currency currency;

    @Column(name = "balance", nullable = false)
    @NotNull
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "enabled", nullable = false)
    @NotNull
    private boolean enable = true;

    public Account(Integer accountId, String name, String accountNumber, Currency currency, BigDecimal balance) {
        super(accountId);
        this.name = name;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
    }

    public Account(){}

    public Account(String name){
        this(null, name, DEFAULT_ACCOUNT_NUMBER, null, ZERO_BALANCE);
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
