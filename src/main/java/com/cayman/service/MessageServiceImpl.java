package com.cayman.service;


import com.cayman.dto.MessageTransferObject;
import com.cayman.entity.Message;
import com.cayman.entity.User;
import com.cayman.repository.MessageRepository;
import com.cayman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message get(int userId, int messageId) {
        return messageRepository.get(userId, messageId);
    }

    @Override
    public List<Message> getAll(int userId) {
        return messageRepository.getAll(userId);
    }

    @Override
    @Transactional
    public MessageTransferObject sendToAll(Message message) {
        List<User> userList = userRepository.getAll();

        MessageTransferObject result = new MessageTransferObject(message);
        result.setUserList(userList);
        for (User user : userList) {
            messageRepository.save(new Message(message.getSubject(), message.getText()), user.getId());
        }

        return result;
    }

    @Override
    @Transactional
    public MessageTransferObject sendToUser(Message message, int userId) {
        MessageTransferObject result = new MessageTransferObject(message);
        List<User> userList = new ArrayList<>();
        userList.add(userRepository.getUser(userId));
        result.setUserList(userList);
        messageRepository.save(message, userId);
        return result;
    }

    @Override
    @Transactional
    public boolean delete(int userId, int messageId) {
        return messageRepository.delete(userId, messageId);
    }

    @Override
    @Transactional
    public Message markAsNewOrOld(int messageId, int userId, boolean newOrOld) {
        Message message = get(userId, messageId);
        message.setNewMessage(newOrOld);
        return messageRepository.save(message, userId);
    }
}
