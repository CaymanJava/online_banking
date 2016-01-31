package com.cayman.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int userId;

    @Column(name = "login", nullable = false)
    @NotEmpty
    private String login;

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    private String lastName;

    @Column(name = "email", nullable = false)
    @NotEmpty
    private String email;

    @Column(name = "registered", nullable = false)
    @NotEmpty
    private Date registered;

    @Column(name = "enabled", nullable = false)
    @NotEmpty
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Account> accountList;

    @OneToOne (cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
    private Role role;

    public User() {
    }

    public User(Integer userId, String login, String password, String firstName,
                String lastName, String email, Date registered, Role role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registered = registered;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
