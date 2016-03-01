package com.cayman.web.message;


import com.cayman.dto.MessageTransferObject;
import com.cayman.entity.Message;
import com.cayman.service.MessageService;
import com.cayman.web.CommonController;
import com.cayman.web.LoggedUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractMessageController extends CommonController {
    protected final static Logger LOG = Logger.getLogger(AbstractMessageController.class);

    @Autowired
    protected MessageService messageService;

    public Message get(int messageId) {
        int userId = LoggedUser.id();
        LOG.info("get message userId = " + userId + "; messageId = " + messageId );
        return messageService.get(userId, messageId);
    }

    public List<Message> getAll() {
        int userId = LoggedUser.id();
        LOG.info("get all message for user id = " + userId);
        return messageService.getAll(userId);
    }

    public MessageTransferObject sendToAll(Message message) {
        LOG.info("send message to all users");
        return messageService.sendToAll(message);
    }

    public MessageTransferObject sendToUser(Message message, int userId){
        LOG.info("send message to user id = " + userId);
        return messageService.sendToUser(message, userId);
    }
    public boolean delete(int messageId) {
        int userId = LoggedUser.id();
        LOG.info("delete message id = " + messageId + "; userId = " + userId);
        return messageService.delete(userId, messageId);
    }
    public Message markAsNewOrOld(int messageId, boolean newOrOld) {
        int userId = LoggedUser.id();
        LOG.info("change message status id = " + messageId + "; userId = " + userId);
        return messageService.markAsNewOrOld(messageId, userId, newOrOld);
    }

}
