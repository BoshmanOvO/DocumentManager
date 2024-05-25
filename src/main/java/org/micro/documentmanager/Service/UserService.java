package org.micro.documentmanager.Service;


import org.micro.documentmanager.Models.RoleEntity;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name); // username
}
