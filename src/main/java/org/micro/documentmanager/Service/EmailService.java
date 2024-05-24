package org.micro.documentmanager.Service;

public interface EmailService {
    void sendNewAccountEmail(String name, String to, String token_send);
    void sendPasswordResetEmail(String name, String to, String token_send);

}
