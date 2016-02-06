package com.cayman.util;

import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.AccountHistory;

import java.util.ArrayList;
import java.util.List;


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
                history.getSenderAmount(), history.getAmountAfterOperationOnSender(), history.getCommission(),
                history.getFromUserId(), history.getFromAccountId()
        );
    }

    public static AccountHistoryTransferObject getDTODebitTypeFromHistory(AccountHistory history){
        return new AccountHistoryTransferObject(
                true,
                history.getOperationTime(),
                history.getRecipientCurrency(),
                history.getToAccountNumber(), history.getFromAccountNumber(),
                history.getComment(),
                history.getRecipientAmount(), history.getAmountAfterOperationInRecipient(), Account.ZERO_BALANCE,
                history.getToUserId(), history.getToAccountId()
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
}
