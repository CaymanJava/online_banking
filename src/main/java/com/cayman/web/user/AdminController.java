package com.cayman.web.user;


import com.cayman.entity.Role;
import com.cayman.entity.User;
import com.cayman.util.TimeUtil;
import com.cayman.util.exceptions.CannotDeleteEntityException;
import com.cayman.web.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractUserController {

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanel() {
        return "adminPanel";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/commission", method = RequestMethod.GET)
    public String getCommissionAccounts(Model model) {
        model.addAttribute("commissionAccounts", accountService.getAll(LoggedUser.ADMIN_ID));
        return "commissionAccounts";
    }

    @RequestMapping(value = "/commission/history", method = RequestMethod.GET)
    public String getHistoryCommission(HttpServletRequest request, Model model) {
        int accountId = getAccountId(request);
        model.addAttribute("commissionHistory", super.getHistoryCommissionBetween(
                TimeUtil.MIN_DATE, LocalTime.MIN, TimeUtil.MAX_DATE, LocalTime.MAX, accountId));
        model.addAttribute("accountId", accountId);
        return "commissionAccountHistory";
    }

    @RequestMapping(value = "/commission/history/filter", method = RequestMethod.POST)
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

    @RequestMapping(value = "/users/settings", method = RequestMethod.GET)
    public String userSettings(HttpServletRequest request, Model model){
        int userId =  getUserId(request);
        User user = super.get(getUserId(request));
        model.addAttribute("user", user);
        model.addAttribute("accountList", super.getUsersAccount(userId));
        return "userSettings";
    }

    @RequestMapping(value = "users/delete", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request) {
        try {
            super.delete(getUserId(request));
        } catch (CannotDeleteEntityException e) {
            return "errors/cannotDeleteUser";
        }
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/users/settings/block", method = RequestMethod.GET)
    public String blockAccount(HttpServletRequest request, Model model){
        super.blockAccount(getUserId(request), getAccountId(request));
        return userSettings(request, model);
    }

    @RequestMapping(value = "/users/settings/unBlock", method = RequestMethod.GET)
    public String unBlockAccount(HttpServletRequest request, Model model){
        super.unBlockAccount(getUserId(request), getAccountId(request));
        return userSettings(request, model);
    }


    @RequestMapping(value = "/users/settings/history", method = RequestMethod.GET)
    public String userAccountMenu(HttpServletRequest request, Model model){
        int userId = getUserId(request);
        int accountId = getAccountId(request);
        model.addAttribute("accountHistory", super.getAccountHistoryForAdmin(userId, accountId));
        model.addAttribute("userId", userId);
        model.addAttribute("accountId", accountId);
        return "adminAccountHistory";
    }

    @RequestMapping(value = "/users/settings/history/filter", method = RequestMethod.POST)
    public String getFilteredHistory(HttpServletRequest request, Model model) {
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

    //пока не работает
    @RequestMapping(value = "/users/settings/edit", method = RequestMethod.POST)
    public String editUser(HttpServletRequest request){
        int userId = getUserId(request);
        User user = super.get(userId);

        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setEnabled(request.getParameter("enabled").equals("true"));

        Set<Role> userRoles = user.getRoles();

        if(request.getParameter("admin").equals("true")) {
            userRoles.add(Role.ROLE_ADMIN);
        } else {
            if(userRoles.contains(Role.ROLE_ADMIN)) {
                userRoles.remove(Role.ROLE_ADMIN);
            }
        }
        user.setRoles(userRoles);
        super.update(user);
        return "redirect:/admin/users";
    }

    public int getUserId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("userId"));
        return Integer.parseInt(id);
    }

    public int getAccountId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("accountId"));
        return Integer.parseInt(id);
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
