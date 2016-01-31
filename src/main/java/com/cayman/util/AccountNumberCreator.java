package com.cayman.util;


import com.cayman.entity.Currency;

public class AccountNumberCreator {

    private AccountNumberCreator(){
    }

    public static String createAccountNumber(int userId, int accountId, Currency currency) {
        String currencyPart;
        switch (currency) {
            case UAH: {
                currencyPart = "1010";
                break;
            }
            case USD: {
                currencyPart = "2020";
                break;
            }
            case EUR: {
                currencyPart = "3030";
                break;
            }
            case PLN: {
                currencyPart = "4040";
                break;
            }
            case RUB: {
                currencyPart = "5050";
                break;
            }
            default: {
                currencyPart = "6060";
            }
        }

        StringBuilder sb = new StringBuilder();

        String userPart = String.valueOf(userId);
        int userPartLength = userPart.length();

        String accountPart = String.valueOf(accountId);
        int accountPartLength = accountPart.length();

        sb.append(currencyPart);
        for (int i = 0; i < (16 - userPartLength - accountPartLength) ; i++) {
            sb.append(0);
        }
        sb.append(userPart);
        sb.append(accountPart);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(createAccountNumber(1, 1, Currency.USD));
        System.out.println(createAccountNumber(1, 2, Currency.PLN));
        System.out.println(createAccountNumber(1, 3, Currency.UAH));
        System.out.println(createAccountNumber(1, 4, Currency.EUR));
        System.out.println(createAccountNumber(1, 5, Currency.RUB));

        System.out.println(createAccountNumber(2, 6, Currency.RUB));
        System.out.println(createAccountNumber(2, 7, Currency.UAH));

        System.out.println(createAccountNumber(3, 8, Currency.USD));
        System.out.println(createAccountNumber(3, 9, Currency.EUR));


    }
}
