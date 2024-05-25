package org.micro.documentmanager.Service.Impl;


import lombok.extern.slf4j.Slf4j;
import org.micro.documentmanager.Enumeration.Authority;
import org.micro.documentmanager.Enumeration.UserEventType;
import org.micro.documentmanager.Events.UserEvents;
import org.micro.documentmanager.Exception.ApiException;
import org.micro.documentmanager.Models.ConfirmationEntity;
import org.micro.documentmanager.Models.CredentialEntity;
import org.micro.documentmanager.Models.RoleEntity;
import org.micro.documentmanager.Models.UserEntity;
import org.micro.documentmanager.Repository.ConfirmationRepo;
import org.micro.documentmanager.Repository.CredentialRepo;
import org.micro.documentmanager.Repository.RoleRepo;
import org.micro.documentmanager.Repository.UserRepo;
import org.micro.documentmanager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.micro.documentmanager.Utils.UserUtils.createUserEntity;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired private UserRepo userRepo;
    @Autowired private RoleRepo roleRepo;
    @Autowired private CredentialRepo credentialRepo;
    @Autowired private ConfirmationRepo confirmationRepo;

    @Autowired private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepo.save(createNewUser(firstName, lastName, email));
        var credentialEntity = new CredentialEntity((UserEntity) userEntity, password);
        credentialRepo.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity((UserEntity) userEntity);
        confirmationRepo.save(confirmationEntity);
        applicationEventPublisher.publishEvent(new UserEvents((UserEntity) userEntity, UserEventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));

    }

    @Override
    public RoleEntity getRoleName(String name) {
        var userRole = roleRepo.findByNameIgnoreCase(name);
        return userRole.orElseThrow(() -> new ApiException("Role not found"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }

}