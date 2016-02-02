package com.cayman.util;


import com.cayman.entity.Currency;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AccountUtil {
    private static Properties rateProperty = new Properties();
    private static Properties commissionProperty = new Properties();

    public static void main(String[] args) {
        System.out.println(createBigDecimal(1.4));
    }

    private AccountUtil(){
    }

    public static boolean checkBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    // 0 - amount after taking commission
    // 1 - commission amount
    public static List<BigDecimal> countCommissionRate(BigDecimal amount) {
        List<BigDecimal> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/commission/rates.properties")){
            rateProperty.load(fis);
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
        String result = "";
        try (FileInputStream fis = new FileInputStream("src/main/resources/commission/accounts.properties")){
            commissionProperty.load(fis);
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
}
