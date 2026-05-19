package com.swastik.nexii.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
