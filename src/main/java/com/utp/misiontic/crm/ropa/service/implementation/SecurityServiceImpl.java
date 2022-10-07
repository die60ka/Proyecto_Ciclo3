package com.utp.misiontic.crm.ropa.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.utp.misiontic.crm.ropa.controller.dto.UserRequest;
import com.utp.misiontic.crm.ropa.controller.dto.UserResponse;
import com.utp.misiontic.crm.ropa.model.entity.User;
import com.utp.misiontic.crm.ropa.model.repository.UserRepository;
import com.utp.misiontic.crm.ropa.service.SecurityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    @Override
    public UserResponse validateUser(String username, String password) {
        // TODO Auto-generated method stub
        var userOp = userRepository.findById(username);
        if(userOp.isEmpty()) {
            throw new RuntimeException("Usuario no existe");
        }

        var user = userOp.get();
        if(!user.getActive()) {
            throw new RuntimeException("Usuario inactivo");
        }
        if(!user.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales invalidas");
        }

        return UserResponse.builder()
            .username(user.getUsername())
            .name(user.getName())
            .email(user.getEmail())
            .admin(user.getAdmin())
            .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(u -> UserResponse.builder()
                .username(u.getUsername())
                .name(u.getName())
                .email(u.getEmail())
                .admin(u.getAdmin())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        var userOp = userRepository.findById(username);
        if(userOp.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }
        var user = userOp.get();
        return UserResponse.builder()
            .username(user.getUsername())
            .name(user.getName())
            .email(user.getEmail())
            .admin(user.getAdmin())
            .build();
    }

    @Override
    public void createUser(UserRequest user) {

        var userOp = userRepository.findById(user.getUsername());
        if(userOp.isPresent()){
            throw new RuntimeException("El usuario ya existe");
        }

        userOp = userRepository.findByEmail(user.getEmail());
        if(userOp.isPresent()){
            throw new RuntimeException("El correo ya se encuentra registrado");
        }

        var userDb = new User();
        userDb.setUsername(user.getUsername());
        userDb.setPassword(user.getPassword());
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setActive(true);
        userDb.setAdmin(user.getAdmin());
        userDb = userRepository.save(userDb);
    }

    @Override
    public void updateUser(UserRequest user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteUser(String username) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void activateUser(String username) {
        var userOp = userRepository.findById(username);
        if(userOp.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }
        var user = userOp.get();
        user.setActive(true);
        userRepository.save(user);
        
    }

    @Override
    public void inactivateUser(String username) {
        var userOp = userRepository.findById(username);
        if(userOp.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }
        var user = userOp.get();
        user.setActive(false);
        userRepository.save(user);
    }
}
