package com.cayman.dto;


import com.cayman.entity.Message;
import com.cayman.entity.User;

import java.util.List;

public class MessageTransferObject {
    private Message message;
    private List<User> userList;

    public MessageTransferObject(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
