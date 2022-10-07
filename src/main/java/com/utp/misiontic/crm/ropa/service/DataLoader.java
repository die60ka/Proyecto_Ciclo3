package com.utp.misiontic.crm.ropa.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.utp.misiontic.crm.ropa.model.entity.User;
import com.utp.misiontic.crm.ropa.model.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        var migrate = false;
        if (migrate) {
            loadUser();
        }
        // TODO Auto-generated method stub
        
    }

    private void loadUser() {
        userRepository.save(new User("admin", "admin123", "Administrador",
                "admin@crmclothes.com", true, true));
    }
    
}
