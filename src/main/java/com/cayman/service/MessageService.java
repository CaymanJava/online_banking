package com.cayman.service;


import com.cayman.dto.MessageTransferObject;
import com.cayman.entity.Message;

import java.util.List;

public interface MessageService {
    Message get(int userId, int messageId);
    List<Message> getAll(int userId);
    MessageTransferObject sendToAll(Message message);
    MessageTransferObject sendToUser(Message message, int userId);
    boolean delete(int userId, int messageId);
    Message markAsNewOrOld(int messageId, int userId, boolean newOrOld);
}
