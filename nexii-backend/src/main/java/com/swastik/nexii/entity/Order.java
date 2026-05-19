package com.swastik.nexii.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    
    private Double totalAmount;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    
    private String deliveryAddress;
    private String paymentId;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }
}
