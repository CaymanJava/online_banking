package com.cayman.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/accounts")
public class JspAccountController extends AbstractAccountController {

    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("accountList", super.getAll());
        return "accountList";
    }
}
