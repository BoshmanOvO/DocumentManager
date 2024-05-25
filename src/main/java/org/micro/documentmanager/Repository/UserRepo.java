package org.micro.documentmanager.Repository;


import org.micro.documentmanager.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUserId(String userId);
    Optional<UserEntity> findUserByEmailIgnoreCase(String userEmail);
}
