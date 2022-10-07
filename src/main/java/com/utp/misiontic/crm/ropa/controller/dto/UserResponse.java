package com.utp.misiontic.crm.ropa.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private String username;
    private String name;
    private String email;
    private Boolean admin;
}
