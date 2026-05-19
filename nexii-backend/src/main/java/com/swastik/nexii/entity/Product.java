package com.swastik.nexii.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;
    
    private String title;
    private String description;
    private String category;
    private String brand;
    
    private Double price;
    private Double discountPrice;
    
    private Integer stockQuantity;
    private String imageUrl;
    
    private LocalDateTime createdAt = LocalDateTime.now();
}
