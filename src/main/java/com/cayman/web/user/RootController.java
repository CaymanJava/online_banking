package com.cayman.web.user;

import com.cayman.entity.User;
import com.cayman.util.MailSender;
import com.cayman.web.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class RootController extends AbstractUserController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:accounts";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(HttpServletRequest request, Model model) {
        User user = super.get(LoggedUser.id());
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String subject = request.getParameter("subject");
        String mail = request.getParameter("mail");

        StringBuilder sb = new StringBuilder();
        sb.append("From: ");
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append("\n");
        sb.append("Mail:\n");
        sb.append(mail);

        MailSender mailSender = MailSender.getInstance();

        mailSender.send(subject, sb.toString());

        model.addAttribute("subject", subject);
        model.addAttribute("mail", mail);

        return "mailSent";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(HttpServletRequest request) {
        super.create(request);
        return "login";
    }

    @RequestMapping(value= "/edit", method = RequestMethod.GET)
    public String getUserForEdit(Model model) {
        User user = super.get(LoggedUser.id());
        model.addAttribute("user", user);
        return "editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String editProfile(HttpServletRequest request) {
        super.update(request);
        return "accountList";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateUserProfile(HttpServletRequest request) {
        super.update(request);
        return "redirect:/accounts";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", user.getName());
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value = "readme", method = RequestMethod.GET)
    public String readme(){
        return "readme";
    }

    @RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
    public String faviconRedirect(){
        return "redirect:accounts";
    }

}
