package com.microservice.backend.repos;

import com.microservice.backend.entity.UserPrivileges;
import org.springframework.data.repository.CrudRepository;

public interface UserPrivilegesRepository extends CrudRepository<UserPrivileges, Long> {
    UserPrivileges findByUsername(String username);
}
