package com.cayman.web.account;

import com.cayman.entity.Account;
import com.cayman.entity.Currency;
import com.cayman.util.AccountUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Objects;

@Controller
@RequestMapping(value = "/accounts")
public class JspAccountController extends AbstractAccountController {

    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("accountList", super.getAll());
        return "accountList";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String editForUpdate(HttpServletRequest request, Model model){
        model.addAttribute("account", super.get(getId(request)));
        return "accountEdit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String editForCreate(Model model) {
        model.addAttribute("account", new Account("My New Account"));
        return "accountCreate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateOrCreate(HttpServletRequest request) {
        String id = request.getParameter("id");
        String accountNumber = request.getParameter("accountNumber");
        String currency = request.getParameter("currency");
        Account account = new Account(id.isEmpty() ? null : Integer.parseInt(id),
                request.getParameter("name"),
                accountNumber.isEmpty() ? null : accountNumber,
                Currency.valueOf(currency),
                AccountUtil.createBigDecimal(Double.parseDouble(request.getParameter("balance"))));
        if (account.isNew()) {
            super.create(account);
        } else {
            super.update(account);
        }
        return "redirect:/accounts";
    }

    @RequestMapping(value = "update/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request){
        int id = getId(request);
        BigDecimal balance = get(id).getBalance();
        if (balance.compareTo(Account.ZERO_BALANCE) == 0) {
            super.delete(id);
            return "redirect:/accounts";
        }
        else {
            return "errors/cannotDelete";
        }

    }

    private int getId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(id);
    }
}
