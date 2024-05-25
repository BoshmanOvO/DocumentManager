package org.micro.documentmanager.Repository;


import org.micro.documentmanager.Models.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CredentialRepo extends JpaRepository<CredentialEntity, Long> {
    Optional<CredentialEntity> findCredentialByUserId(Long userId);
}
