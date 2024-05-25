package org.micro.documentmanager.Repository;


import org.micro.documentmanager.Models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByNameIgnoreCase(String name);
}
