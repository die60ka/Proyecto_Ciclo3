package com.utp.misiontic.crm.ropa.service;

import java.util.List;

import com.utp.misiontic.crm.ropa.controller.dto.UserRequest;
import com.utp.misiontic.crm.ropa.controller.dto.UserResponse;

public interface SecurityService {

    UserResponse validateUser(String username, String password);

    List<UserResponse> getAllUsers();

    UserResponse getUserByUsername(String username);

    void createUser(UserRequest user);

    void updateUser(UserRequest user);

    void deleteUser(String username);

    void  activateUser(String username);

    void inactivateUser(String username);
}
