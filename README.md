
# Grocery Order Management System

A RESTful backend application built using Java, Spring Boot, Spring Data JPA, and MySQL to manage customers, grocery items, and orders.

## Technologies Used

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Gradle
- REST APIs

## Features

### Customer Management
- Create a customer
- Retrieve all customers
- Retrieve customer by ID
- Update customer
- Delete customer

### Grocery Item Management
- Create grocery items
- Retrieve all grocery items
- Retrieve grocery item by ID
- Update grocery item
- Delete grocery item

### Order Management
- Create orders
- Retrieve all orders
- Retrieve order by ID
- Update orders
- Delete orders
- Calculate order total price

### Additional Features
- Entity relationships
- Input validation
- Global exception handling
- RESTful API design

## Database Relationships

- One Customer can have multiple Orders.
- One Order can contain multiple Grocery Items.
- One Grocery Item can belong to multiple Orders.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/customers | Create customer |
| GET | /api/customers | Get all customers |
| GET | /api/customers/{id} | Get customer by ID |
| PUT | /api/customers/{id} | Update customer |
| DELETE | /api/customers/{id} | Delete customer |
| POST | /api/grocery-items | Create grocery item |
| GET | /api/grocery-items | Get all grocery items |
| GET | /api/grocery-items/{id} | Get grocery item by ID |
| PUT | /api/grocery-items/{id} | Update grocery item |
| DELETE | /api/grocery-items/{id} | Delete grocery item |
| POST | /api/orders | Create order |
| GET | /api/orders | Get all orders |
| GET | /api/orders/{id} | Get order by ID |
| PUT | /api/orders/{id} | Update order |
| DELETE | /api/orders/{id} | Delete order |

## Database Configuration

Create a MySQL database:

    CREATE DATABASE grocery_db;

Configure your database credentials in application.properties.

Do not upload your actual database password to GitHub.

## Run the Application

Clone the repository:

    git clone YOUR_GITHUB_REPOSITORY_URL

Navigate to the project directory:

    cd grocery-order-management

Build the application:

    ./gradlew clean build

Run the application:

    ./gradlew bootRun

The application runs at:

    http://localhost:8080
