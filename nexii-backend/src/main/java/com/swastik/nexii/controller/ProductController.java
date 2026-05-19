package com.swastik.nexii.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/public/search")
    public ResponseEntity<?> searchProducts(@RequestParam String query) {
        // Implementation for public product search
        return ResponseEntity.ok("Search results for: " + query);
    }
    
    @PostMapping("/")
    public ResponseEntity<?> addProduct() {
        // Only accessible by SELLER
        return ResponseEntity.ok("Product added successfully");
    }
}
