package com.cayman.util;

import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.AccountHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AccountHistoryTransferUtil {

    private AccountHistoryTransferUtil(){
    }

    public static AccountHistoryTransferObject getDTOCreditTypeFromHistory(AccountHistory history) {
        return new AccountHistoryTransferObject(
                false,
                history.getOperationTime(),
                history.getSenderCurrency(),
                history.getFromAccountNumber(), history.getToAccountNumber(),
                history.getComment(),
                AccountUtil.createBigDecimal(history.getSenderAmount()),
                AccountUtil.createBigDecimal(history.getAmountAfterOperationOnSender()),
                AccountUtil.createBigDecimal(history.getCommission()),
                history.getFromAccountId()
        );
    }

    public static AccountHistoryTransferObject getDTODebitTypeFromHistory(AccountHistory history){
        return new AccountHistoryTransferObject(
                true,
                history.getOperationTime(),
                history.getRecipientCurrency(),
                history.getToAccountNumber(), history.getFromAccountNumber(),
                history.getComment(),
                AccountUtil.createBigDecimal(history.getRecipientAmount()),
                AccountUtil.createBigDecimal(history.getAmountAfterOperationInRecipient()), Account.ZERO_BALANCE,
                history.getToAccountId()
        );
    }

    public static List<AccountHistoryTransferObject> getDTOListFromHistoryList(List<AccountHistory> historyList, int accountId) {
        List<AccountHistoryTransferObject> result = new ArrayList<>();
        for (AccountHistory history : historyList) {
            if (history.getFromAccountId() == accountId){
                result.add(AccountHistoryTransferUtil.getDTOCreditTypeFromHistory(history));
            } else {
                result.add(AccountHistoryTransferUtil.getDTODebitTypeFromHistory(history));
            }
        }
        return result;
    }

    public static List<AccountHistoryTransferObject> getDTOCommissionList(List<AccountHistory> histories){
        return histories.stream().map(history -> new AccountHistoryTransferObject(
                true,
                history.getOperationTime(),
                history.getSenderCurrency(),
                history.getFromAccountNumber(), history.getToAccountNumber(),
                history.getComment(),
                AccountUtil.createBigDecimal(history.getSenderAmount()),
                AccountUtil.createBigDecimal(history.getAmountAfterOperationOnSender()),
                AccountUtil.createBigDecimal(history.getCommission()),
                history.getCommissionAccountId()
        )).collect(Collectors.toList());
    }
}
