package com.cayman.web.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "admin/users")
public class AdminAjaxController extends AbstractUserController {

    @RequestMapping(value = "settings/changeAccountStatus", method = RequestMethod.GET)
    public void changeAccountStatus(@RequestParam("accountId") int accountId, @RequestParam("userId") int userId) {
        super.changeAccountStatus(userId, accountId);
    }

    @RequestMapping(value = "settings/changeUserStatus", method = RequestMethod.GET)
    public void changeUserStatus(@RequestParam("userId") int userId) {
        super.changeUserStatus(userId);
    }

    @RequestMapping(value = "settings/changeUserRole", method = RequestMethod.GET)
    public void changeUserRole(@RequestParam("userId") int userId){
        super.changeUserRole(userId);
    }
}
