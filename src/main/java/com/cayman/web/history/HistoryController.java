package com.cayman.web.history;


import com.cayman.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;


@Controller
public class HistoryController extends AbstractHistoryController {

    @RequestMapping(value = "accounts/history", method = RequestMethod.GET)
    public String getAccountHistory(HttpServletRequest request, Model model) {
        int id = getAccountId(request);
        model.addAttribute("accountHistory", super.getAccountHistory(id));
        model.addAttribute("accountId", id);
        return "accountHistory";
    }

    @RequestMapping(value = "accounts/history", method = RequestMethod.POST)
    public String getFilteredHistory(HttpServletRequest request, Model model) {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request), TimeUtil.MIN_DATE);
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request), TimeUtil.MAX_DATE);
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request), LocalTime.MIN);
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request), LocalTime.MAX);
        String option = request.getParameter("option");
        int accountId = getAccountId(request);
        model.addAttribute("accountHistory", super.getHistoryBetweenWithOption(
                startDate, startTime,
                endDate, endTime,
                accountId, option));
        model.addAttribute("accountId", accountId);
        return "accountHistory";
    }

    @RequestMapping(value = "admin/commission/history", method = RequestMethod.GET)
    public String getHistoryCommission(HttpServletRequest request, Model model) {
        int accountId = getAccountId(request);
        model.addAttribute("commissionHistory", super.getHistoryCommissionBetween(
                TimeUtil.MIN_DATE, LocalTime.MIN, TimeUtil.MAX_DATE, LocalTime.MAX, accountId));
        model.addAttribute("accountId", accountId);
        return "commissionAccountHistory";
    }

    @RequestMapping(value = "admin/commission/history", method = RequestMethod.POST)
    public String getHistoryCommissionWithFilter(HttpServletRequest request, Model model) {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request), TimeUtil.MIN_DATE);
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request), TimeUtil.MAX_DATE);
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request), LocalTime.MIN);
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request), LocalTime.MAX);
        int accountId = getAccountId(request);

        model.addAttribute("commissionHistory", super.getHistoryCommissionBetween(
                startDate, startTime,
                endDate, endTime,
                accountId));
        model.addAttribute("accountId", accountId);
        return "commissionAccountHistory";
    }


    @RequestMapping(value = "admin/users/settings/history", method = RequestMethod.GET)
    public String userAccountHistory(HttpServletRequest request, Model model){
        int userId = getUserId(request);
        int accountId = getAccountId(request);
        model.addAttribute("accountHistory", super.getAccountHistoryForAdmin(userId, accountId));
        model.addAttribute("userId", userId);
        model.addAttribute("accountId", accountId);
        return "adminAccountHistory";
    }

    @RequestMapping(value = "admin/users/settings/history", method = RequestMethod.POST)
    public String getFilteredHistoryForAdmin(HttpServletRequest request, Model model) {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request), TimeUtil.MIN_DATE);
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request), TimeUtil.MAX_DATE);
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request), LocalTime.MIN);
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request), LocalTime.MAX);
        String option = request.getParameter("option");

        int userId = getUserId(request);
        int accountId = getAccountId(request);

        model.addAttribute("accountHistory", super.getHistoryBetweenWithOptionForAdmin(
                startDate, startTime,
                endDate, endTime,
                accountId, userId,
                option));
        model.addAttribute("accountId", accountId);
        model.addAttribute("userId", userId);
        return "adminAccountHistory";
    }
}
