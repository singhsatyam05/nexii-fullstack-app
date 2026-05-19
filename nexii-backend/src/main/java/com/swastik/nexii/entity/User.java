package com.swastik.nexii.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private String fullName;
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private Role role; // BUYER, SELLER, ADMIN
    
    private Double latitude;
    private Double longitude;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public enum Role {
        BUYER, SELLER, ADMIN
    }
}
