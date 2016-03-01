package com.cayman.util.exceptions;


import com.cayman.entity.Account;
import com.cayman.entity.User;

import java.math.BigDecimal;

public class ExceptionUtils {

    private ExceptionUtils(){
    }

    public static void checkForZeroBalance(Account account) {
        if (account.getBalance().compareTo(Account.ZERO_BALANCE) != 0) {
            throw new CannotDeleteEntityException();
        }
    }

    public static void checkBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new NotEnoughMoneyInTheAccountException();
        }
        checkForNegativeAndZeroAmount(amount);
    }

    public static void checkAccountForBlocking(Account account) {
        if (!account.isEnable()) {
            throw new NotAvailableAccountException();
        }
    }

    public static void checkForNegativeAndZeroAmount(BigDecimal amount) {
        if (amount.compareTo(Account.ZERO_BALANCE) <= 0) {
            throw new IncorrectAmountException();
        }
    }

    public static void checkSuperAdmin(User user) {
        if (user.getRole().name().equals("ROLE_SUPER_ADMIN")) {
            throw new DoNotTouchSuperAdminException();
        }
    }

}
