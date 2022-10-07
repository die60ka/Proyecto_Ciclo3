package com.utp.misiontic.crm.ropa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.misiontic.crm.ropa.controller.dto.LoginRequest;
import com.utp.misiontic.crm.ropa.service.SecurityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    private final SecurityService securityService;

  @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest user){
        try {
            var response = securityService.validateUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
        }
    }

    @PostMapping("/fake")
    public ResponseEntity<?> fakeLogin() {
        return ResponseEntity.badRequest().build();
    }
}
