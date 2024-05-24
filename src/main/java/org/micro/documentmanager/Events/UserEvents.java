package org.micro.documentmanager.Events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.micro.documentmanager.Enumeration.UserEventType;
import org.micro.documentmanager.Models.UserEntity;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvents {
    private UserEntity user;
    private UserEventType eventType;
    private Map<?, ?> data;

}
