package org.micro.documentmanager.Service.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.micro.documentmanager.Exception.ApiException;
import org.micro.documentmanager.Service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.micro.documentmanager.Utils.EmailUtils.getAccountResetEmailBody;
import static org.micro.documentmanager.Utils.EmailUtils.getNewAccountEmailBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String NEW_MAIL_VERIFICATION_SUBJECT = "Please verify your email address";
    private static final String PASSWORD_RESET_SUBJECT = "Reset your password";

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String to, String token_send) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(NEW_MAIL_VERIFICATION_SUBJECT);
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setText(getNewAccountEmailBody(name, host, token_send));
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Failed to send email");
        }
    }



    @Override
    @Async
    public void sendPasswordResetEmail(String name, String to, String token_send) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(PASSWORD_RESET_SUBJECT);
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setText(getAccountResetEmailBody(name, host, token_send));
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Failed to send email");
        }
    }


}
