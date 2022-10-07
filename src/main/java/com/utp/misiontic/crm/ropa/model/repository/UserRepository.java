package com.utp.misiontic.crm.ropa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.misiontic.crm.ropa.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPasswordAndActiveIsTrue(String username, String password);

    Optional<User> findByEmail(String email);
    
}
