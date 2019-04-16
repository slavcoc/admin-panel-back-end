package com.adminapp.adminapp.configs;

import com.adminapp.adminapp.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    @Autowired
    public MailSender emailSender;

    public void sendSimpleMessage(Email emailconfig) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailconfig.getTo());
        message.setSubject(emailconfig.getSubject());
        message.setText("User sending this email is: " + emailconfig.getFrom() + "\n" + emailconfig.getText());
        message.setFrom(emailconfig.getFrom());
        emailSender.send(message);

    }
}
