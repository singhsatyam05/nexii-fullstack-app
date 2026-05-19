# NEXII Full-Stack Backend Architecture

Welcome to the **NEXII Hyperlocal Electronics Marketplace** backend repository. This backend is built with Spring Boot, Core Java, Spring Security (JWT), and MySQL to support a scalable, production-grade Android ecosystem.

## 🏗️ Tech Stack

*   **Framework:** Spring Boot 3.2.4 (Java 17)
*   **Database:** MySQL 8 with Spring Data JPA (Hibernate)
*   **Security:** Spring Security & JWT (JSON Web Tokens)
*   **Build Tool:** Maven
*   **Boilerplate Reduction:** Lombok
*   **Notifications:** Firebase Cloud Messaging (FCM) *[Integration Ready]*

## 📂 Project Structure (Clean Architecture)

```
nexii-backend/
├── pom.xml                                   # Maven Configuration
└── src/main/java/com/swastik/nexii/
    ├── NexiiApplication.java                 # Entry Point
    ├── config/                               # Cors, FCM, Google Maps configs
    ├── controller/                           # REST API Endpoints
    │   ├── AuthController.java               # Register/Login
    │   └── ProductController.java            # Product CRUD
    ├── dto/                                  # Data Transfer Objects (Requests/Responses)
    ├── entity/                               # Database Models (JPA)
    │   ├── User.java                         # Buyer/Seller/Admin Table
    │   ├── Product.java                      # Products Table
    │   └── Order.java                        # Orders Table
    ├── exception/                            # Global Error Handling
    ├── repository/                           # JPA Interfaces
    ├── security/                             # JWT Filters & Auth Configuration
    │   ├── SecurityConfig.java               # RBAC definitions
    │   ├── JwtFilter.java                    # Request interception
    │   └── JwtUtil.java                      # Token generation/validation
    ├── service/                              # Business Logic
    └── util/                                 # Helper classes (GeoUtils, etc.)
```

## 🔐 Security & Authentication Flow

NEXII uses **Role-Based Access Control (RBAC)** to distinguish between `BUYER`, `SELLER`, and `ADMIN`.

1.  **Login (`/api/auth/login`):** User submits email/password.
2.  **Authentication:** `AuthenticationManager` verifies credentials against the database password hash (Bcrypt).
3.  **Token Generation:** `JwtUtil` issues a signed JWT token containing the user's `email` and `role`.
4.  **Authorization:** Subsequent requests include the token in the `Authorization: Bearer <token>` header.
5.  **Filter Interception:** `JwtFilter` validates the token on every request and assigns permissions via `SecurityContextHolder`.

## 🗄️ Database Schema & Entities

The relational database is configured automatically via JPA (`hibernate.ddl-auto=update`).

*   **Users (`users`):** Stores credentials, roles (BUYER, SELLER, ADMIN), and geo-coordinates for nearby logic.
*   **Products (`products`):** Linked to Sellers (Many-to-One), storing pricing, stock, and descriptions.
*   **Orders (`orders`):** Links Buyers, Sellers, and Products. Contains order status (`PENDING`, `DELIVERED`, etc.) and payment transaction IDs.

## 🚀 Key Modules Implemented

### 1. User Authentication System
*   **Registration:** Supports Buyer/Seller signup.
*   **Login:** JWT-based secure session.
*   **Bcrypt:** Passwords are encrypted before persisting.

### 2. Product Management
*   Sellers can add/update products.
*   Buyers can perform hyperlocal searches (`/api/products/public/search?query=...`).

### 3. Inventory & Order Management
*   When an order is created, `stockQuantity` in the Product table is atomically decremented.
*   Sellers track order states up to delivery.

## 🛠️ Setup Instructions

### 1. Database Setup
Create a local MySQL database named `nexii_db`:
```sql
CREATE DATABASE nexii_db;
```

Ensure your `src/main/resources/application.properties` matches your MySQL credentials:
```properties
spring.datasource.username=root
spring.datasource.password=root
```

### 2. Run the Application
Navigate to the `nexii-backend` directory and run via Maven wrapper (or your IDE):
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

## 🧪 Postman Testing Guide

To test the APIs, create a Postman Collection with the following requests:

### 1. Register a User
*   **Method:** POST
*   **URL:** `http://localhost:8080/api/auth/register`
*   **Body (JSON):**
    ```json
    {
      "email": "buyer@nexii.com",
      "password": "password123",
      "fullName": "John Doe",
      "phone": "9876543210",
      "role": "BUYER"
    }
    ```

### 2. Login
*   **Method:** POST
*   **URL:** `http://localhost:8080/api/auth/login`
*   **Body (JSON):**
    ```json
    {
      "email": "buyer@nexii.com",
      "password": "password123"
    }
    ```
*   **Response:** You will receive a JWT `token`. Copy this string.

### 3. Secure Requests (Adding a Product as SELLER)
*   *Note: First register a user with `role: "SELLER"`, then log them in to get their token.*
*   **Method:** POST
*   **URL:** `http://localhost:8080/api/products/`
*   **Headers:**
    *   `Authorization: Bearer <YOUR_COPIED_TOKEN>`
*   **Body (JSON):**
    ```json
    {
       "title": "Sony WH-1000XM5",
       "price": 29999,
       "stockQuantity": 10
    }
    ```

## 📍 Future Implementation Pointers

*   **Hyperlocal Search:** In `LocationUtil`, implement the Haversine formula to calculate the distance between the Buyer's `latitude`/`longitude` and the Seller's store location to filter products within a 30km radius.
*   **Payment Gateway:** Integrate Razorpay or Stripe by adding a new `PaymentController` that securely exchanges transaction hashes.
*   **FCM Notifications:** Add `firebase-admin` dependency and initialize it in `config/FirebaseConfig.java` to send push notifications when `OrderStatus` updates.
