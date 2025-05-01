package com.mens.cloth.mens_cloth.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MailService {


    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    public void sendEmail(String to, String subject, String contant) {


        try {
            System.out.println(to);
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(from);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(contant);
            mailSender.send(mailMessage);

        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to send email", e);
        }


    }
}