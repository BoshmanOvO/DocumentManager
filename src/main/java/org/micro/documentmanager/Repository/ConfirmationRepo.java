package org.micro.documentmanager.Repository;


import org.micro.documentmanager.Models.ConfirmationEntity;
import org.micro.documentmanager.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConfirmationRepo extends JpaRepository<ConfirmationEntity, Long>{
    Optional<ConfirmationEntity> findByKey(String key);
    Optional<ConfirmationEntity> findByUserEntity(UserEntity userEntity);
}
