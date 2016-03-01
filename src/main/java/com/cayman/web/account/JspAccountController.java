package com.cayman.web.account;

import com.cayman.dto.TransactionTransferObject;
import com.cayman.entity.Account;
import com.cayman.entity.Currency;
import com.cayman.util.AccountUtil;
import com.cayman.util.TimeUtil;
import com.cayman.util.exceptions.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Controller
@RequestMapping(value = "/accounts")
public class JspAccountController extends AbstractAccountController {

    @RequestMapping(method = RequestMethod.GET)
    public String accountList(Model model) {
        model.addAttribute("accountList", super.getAll());
        return "accountList";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String update(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("renameAccountId"));
        Account account = get(id);
        String newName = request.getParameter("newName");
        if (newName.isEmpty()) {
            newName = account.getName();
        }
        account.setName(newName);
        super.update(account);
        return "redirect:/accounts";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(HttpServletRequest request) {
        String id = request.getParameter("id");
        String accountNumber = request.getParameter("accountNumber");
        String currency = request.getParameter("currency");
        String money = request.getParameter("balance");
        BigDecimal balance = money.isEmpty() ? Account.ZERO_BALANCE : AccountUtil.createBigDecimal(Double.parseDouble(money));
        Account account = new Account(id.isEmpty() ? null : Integer.parseInt(id),
                request.getParameter("name"),
                accountNumber.isEmpty() ? Account.DEFAULT_ACCOUNT_NUMBER : accountNumber,
                Currency.valueOf(currency),
                balance);
        super.create(account);
        return "redirect:/accounts";
    }

    @RequestMapping(value = "menu/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request){
        super.delete(getAccountId(request));
        return "redirect:/accounts";
    }

    @RequestMapping(value= "/putMoney", method = RequestMethod.POST)
    public String putMoneyIntoAccount(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("putMoneyAccountId"));
        super.putMoney(id, AccountUtil.createBigDecimal(Double.parseDouble(request.getParameter("value"))));
        return "redirect:/accounts";
    }

    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    public String getTransactionInformation(HttpServletRequest request, Model model) {
        TransactionTransferObject dto;
        dto = super.getTransactionInformation(
                    Integer.parseInt(request.getParameter("sendMoneyAccountId")),
                    request.getParameter("recipientAccountNumber"),
                    request.getParameter("comment"),
                    AccountUtil.createBigDecimal(Double.parseDouble(request.getParameter("valueToTransfer"))));
        model.addAttribute("dto", dto);
        return "transactionInformation";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String sendMoney(HttpServletRequest request){
        super.sendMoney(
                    Integer.parseInt(request.getParameter("senderId")),
                    Integer.parseInt(request.getParameter("senderAccountId")),
                    Integer.parseInt(request.getParameter("recipientId")),
                    Integer.parseInt(request.getParameter("recipientAccountId")),
                    Currency.valueOf(request.getParameter("commissionCurrency")),
                    request.getParameter("comment"),
                    AccountUtil.createBigDecimal(Double.parseDouble(request.getParameter("transferAmount"))),
                    AccountUtil.createBigDecimal(Double.parseDouble(request.getParameter("commission"))),
                    AccountUtil.createBigDecimal(Double.parseDouble(request.getParameter("amountForReceive"))));
        return "redirect:/accounts";
    }
}
