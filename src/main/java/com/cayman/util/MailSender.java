package com.cayman.util;


import com.cayman.util.exceptions.CannotSendMessageException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private final static String username = "onlinebanksend@gmail.com";
    private final static String password = "onlinebanksend123";
    private final static String recipient = "onlinebankrecipient@gmail.com";

    private static Properties props;

    private static final MailSender MAIL_SENDER = new MailSender();

    private MailSender() {
        props = new Properties();

        //TLS
        /*props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");*/

        //SSL
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public static MailSender getInstance(){
        return MAIL_SENDER;
    }


    public void send(String subject, String text) {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException ignored) {
            throw new CannotSendMessageException();
        }
    }
}
