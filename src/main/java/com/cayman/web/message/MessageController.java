package com.cayman.web.message;


import com.cayman.entity.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class MessageController extends AbstractMessageController {

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("messageList", super.getAll());
        return "messageList";
    }

    @RequestMapping(value = "messages/more", method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model) {
        Message message = super.get(getMessageId(request));
        super.markAsNewOrOld(message.getId(), false);
        model.addAttribute("message", message);
        return "message";
    }

    @RequestMapping(value = "message/mark", method = RequestMethod.GET)
    public String markAsNew(HttpServletRequest request) {
        super.markAsNewOrOld(getMessageId(request), true);
        return "redirect:/messages";
    }

    @RequestMapping(value = "message/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        super.delete(getMessageId(request));
        return "redirect:/messages";
    }

    @RequestMapping(value = "admin/sendMessageToAll", method = RequestMethod.POST)
    public String sendMessageToAll(HttpServletRequest request, Model model) {
        model.addAttribute("message", super.sendToAll(getMessageFromRequest(request)));
        return "adminMessageSent";
    }

    @RequestMapping(value="admin/sendMessageToUser", method = RequestMethod.POST)
    public String sendMessageToUser(HttpServletRequest request, Model model) {
        model.addAttribute("message", super.sendToUser(getMessageFromRequest(request), getUserId(request)));
        return "adminMessageSent";
    }

    public int getMessageId (HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("messageId"));
        return Integer.parseInt(id);
    }

    public Message getMessageFromRequest(HttpServletRequest request) {
        String subject = request.getParameter("subject");
        String text = request.getParameter("message");
        return new Message(subject, text);
    }
}
