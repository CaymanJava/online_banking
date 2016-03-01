package com.cayman.web.user;


import com.cayman.entity.User;
import com.cayman.util.exceptions.ExceptionUtils;
import com.cayman.web.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractUserController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/commission", method = RequestMethod.GET)
    public String getCommissionAccounts(Model model) {
        model.addAttribute("commissionAccounts", accountService.getAll(LoggedUser.MAIN_ADMIN_ID));
        return "commissionAccounts";
    }

    @RequestMapping(value = "/users/settings", method = RequestMethod.GET)
    public String userSettings(HttpServletRequest request, Model model){
        int userId =  getUserId(request);
        User user = super.get(userId);
        ExceptionUtils.checkSuperAdmin(user);
        model.addAttribute("user", user);
        model.addAttribute("accountList", super.getUsersAccounts(userId));
        return "userSettings";
    }

    @RequestMapping(value = "users/delete", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request) {
        super.delete(getUserId(request));
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String createUserByAdmin(HttpServletRequest request){
        super.create(request);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/users/settings/edit", method = RequestMethod.POST)
    public String editUserByAdmin(HttpServletRequest request){
        super.update(request);
        return "redirect:/admin/users";
    }
}
