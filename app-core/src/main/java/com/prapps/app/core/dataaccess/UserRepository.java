package com.prapps.app.core.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.core.persistence.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserName(String userName);
}
