# Spring Boot E-commerce Project

This is a Spring Boot application for an e-commerce platform.

## Description

This project is a simple e-commerce application built with Spring Boot. It provides RESTful APIs for managing categories.

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Lombok
- ModelMapper
- H2 Database
- Spring Boot Starter Validation

## Prerequisites

- Java 17
- Maven

## How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/sb_project.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd sb_project
   ```
3. **Build the project:**
   ```bash
   mvn clean install
   ```
4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

The application will be running on `http://localhost:8080`.

## API Endpoints

The following API endpoints are available:

- `GET /api/public/categories`: Get all categories
- `POST /api/public/categories`: Create a new category
- `PUT /api/admin/categories/{categoryId}`: Update a category
- `DELETE /api/admin/categories/{categoryId}`: Delete a category

## Database

This application uses the H2 in-memory database. The database console can be accessed at `http://localhost:8080/h2-console`.

**Note:** The database is reset every time the application is restarted.
