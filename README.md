# 🛒 Self-Checkout System – Backend

Backend application developed with **Java and Spring Boot**, designed to simulate a **self-checkout system for a supermarket**.  
The system focuses on business logic such as user roles, discount rules, product management, and order processing.

This project is part of my personal portfolio, with an emphasis on **backend architecture, clean code, and business rule modeling**.

---

## ⚙️ Tech Stack

- Java 17  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Maven  
- Docker & Docker Compose  
- Git / GitHub  

---

## 🧩 Core Features

### 👤 Users & Roles
- User management with different roles:
  - **Customer**
  - **Employee**
  - **Admin**
- Role-based behavior and permissions

### 📦 Products & Categories
- Product creation and management
- Category assignment

### 🛒 Orders & Checkout
- Order creation with multiple items
- Order item pricing and quantity handling
- Total price calculation

### 💸 Discounts System
- Discount logic implemented using a flexible, strategy-based design
- Discounts are configurable and can be activated or deactivated
- Types of discounts currently supported:
  - General discounts: applied directly to products and available to both customers and employees
  - Customer discounts: applied based on active discount configurations
  - Employee discounts: applied according to the active discount assigned

### 🧠 Business Logic Separation
- Controllers handle HTTP concerns
- Services contain business logic
- DTOs and mappers used to decouple layers

---

### 🖥️ Minimal Frontend
- The project includes a minimal frontend layer to interact with the backend API
- Used mainly for testing and manual interaction with core features
- Not intended as a full-featured user interface

---
## 🐳 Docker Support

The project can be run using **Docker Compose**, including:
- Spring Boot application
- PostgreSQL database

This allows easy setup and consistent environments for development.

---

## 🚧 Project Status

🛠 **Actively in development**

Implemented:
- Core entities and relationships
- Main business logic
- Discount system
- Docker setup

Planned:
- API documentation (Swagger / OpenAPI)
- Unit tests (planned to be added progressively)
- General improvements and refactoring as the project evolves

---

## 🎯 Purpose

This project was built to:
- Practice backend development with **Spring Boot**
- Model real-world business rules
- Apply clean architecture principles
- Serve as a portfolio project for backend-oriented roles
