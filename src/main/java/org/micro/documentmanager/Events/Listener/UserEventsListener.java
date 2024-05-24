package org.micro.documentmanager.Events.Listener;


import lombok.RequiredArgsConstructor;
import org.micro.documentmanager.Events.UserEvents;
import org.micro.documentmanager.Service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventsListener {
    private EmailService emailService;

    @EventListener
    public void handleUserEvent(UserEvents userEvents) {
        switch (userEvents.getEventType()) {
            case REGISTRATION -> {
                emailService.sendNewAccountEmail(userEvents.getUser().getFirstName(), userEvents.getUser().getEmail(), (String) userEvents.getData().get("token"));
            }
            case RESETPASSWORD -> {
                emailService.sendPasswordResetEmail(userEvents.getUser().getFirstName(), userEvents.getUser().getEmail(), (String) userEvents.getData().get("token"));
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + userEvents.getEventType());
            }
        }
    }
}
