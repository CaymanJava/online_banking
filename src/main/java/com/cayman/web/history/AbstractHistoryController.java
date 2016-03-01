package com.cayman.web.history;


import com.cayman.dto.AccountHistoryTransferObject;
import com.cayman.service.AccountHistoryService;
import com.cayman.web.CommonController;
import com.cayman.web.LoggedUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public abstract class AbstractHistoryController extends CommonController {
    protected final static Logger LOG = Logger.getLogger(AbstractHistoryController.class);

    @Autowired
    protected AccountHistoryService historyService;

    public List<AccountHistoryTransferObject> getAccountHistory(int accountId) {
        int userId = LoggedUser.id();
        LOG.info("get account history (for user) accountId=" + accountId + " userId=" + userId);
        return historyService.getAllHistoryByAccountId(userId, accountId);
    }

    public List<AccountHistoryTransferObject> getHistoryBetweenWithOption(LocalDate startDate, LocalTime startTime,
                                                                          LocalDate endDate, LocalTime endTime,
                                                                          int accountId, String option) {
        int userId = LoggedUser.id();
        LOG.info("get history between (for user)");
        return historyService.getHistoryBetweenWithOption(
                startDate, startTime,
                endDate, endTime,
                userId, accountId, option);
    }

    public List<AccountHistoryTransferObject> getAccountHistoryForAdmin(int userId, int accountId) {
        LOG.info("get account history (for admin) userId=" + userId + " accountId=" + accountId);
        return historyService.getAllHistoryByAccountId(userId, accountId);
    }

    public List<AccountHistoryTransferObject> getHistoryBetweenWithOptionForAdmin(
            LocalDate startDate, LocalTime startTime,
            LocalDate endDate, LocalTime endTime,
            int accountId, int userId, String option) {
        LOG.info("get history between");
        return historyService.getHistoryBetweenWithOption(startDate, startTime, endDate, endTime, userId, accountId, option);
    }

    public List<AccountHistoryTransferObject> getHistoryCommissionBetween(LocalDate startDate, LocalTime startTime,
                                                                          LocalDate endDate, LocalTime endTime,
                                                                          int accountId) {
        LOG.info("get commission history between");
        return historyService.getCommissionHistoryBetween(startDate, startTime, endDate, endTime, accountId);
    }
}
