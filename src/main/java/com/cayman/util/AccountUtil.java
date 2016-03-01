package com.cayman.util;


import com.cayman.entity.Currency;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AccountUtil {
    private static Properties rateProperty = new Properties();
    private static Properties commissionProperty = new Properties();

    private AccountUtil(){
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
        String accountPart = String.valueOf(accountId);

        sb.append(currencyPart);
        for (int i = 0; i < (16 - userPart.length() - accountPart.length()) ; i++) {
            sb.append(0);
        }
        sb.append(userPart);
        sb.append(accountPart);

        return sb.toString();
    }


    // 0 - amount after taking commission
    // 1 - commission amount
    public static List<BigDecimal> countCommissionRate(BigDecimal amount) {
        List<BigDecimal> result = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("commission/rates.properties")){
            rateProperty.load(input);
            double commissionRate = Double.parseDouble(rateProperty.getProperty("rate"));
            BigDecimal commission = amount.multiply(new BigDecimal(commissionRate));
            commission = commission.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal balance = withdrawMoney(amount, commission);
            result.add(balance);
            result.add(commission);
        } catch (IOException ignored) {}
        return result;
    }

    public static BigDecimal addMoney(BigDecimal balance, BigDecimal amount) {
        return balance.add(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal withdrawMoney(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static String getAccountNumberForCommission(Currency currency){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String result = "";
        try (InputStream input = classLoader.getResourceAsStream("commission/accounts.properties")){
            commissionProperty.load(input);
            result = commissionProperty.getProperty(currency.toString().toLowerCase());
        }catch(IOException ignored) {}
        return result;
    }

    public static BigDecimal createBigDecimal(int value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal createBigDecimal(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal createBigDecimal(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
