package com.cayman.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Message.GET, query = "SELECT mes FROM Message mes WHERE mes.id = :messageId AND mes.user.id = :userId"),
        @NamedQuery(name = Message.GET_ALL, query = "SELECT mes FROM Message mes WHERE mes.user.id = :userId ORDER BY mes.messageTime"),
        @NamedQuery(name = Message.DELETE, query = "DELETE FROM Message mes WHERE mes.id = :messageId AND mes.user.id = :userId")
})

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    public final static String GET = "Message.get";
    public final static String GET_ALL = "Message.getAll";
    public final static String DELETE = "Message.delete";
    public final static String WELCOME_SUBJECT = "Welcome to Cayman Online Bank !!!";
    public final static String WELCOME_TEXT = "Hi! Welcome to my little simulation of online banking :) Please, read README file to more information. Best wishes, Dmitriy Bosenko.";

    @Column(name = "time")
    private LocalDateTime messageTime = LocalDateTime.now();

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @Column(name = "new")
    private boolean newMessage = true;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Message(){}

    public Message (String subject, String text){
        this.subject = subject;
        this.text = text;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage = newMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
