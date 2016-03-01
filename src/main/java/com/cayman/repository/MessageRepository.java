package com.cayman.repository;


import com.cayman.entity.Message;

import java.util.List;


public interface MessageRepository {
    Message get(int userId, int messageId);
    List<Message> getAll(int userId);
    Message save(Message message, int userId);
    boolean delete (int userId, int messageId);

}
