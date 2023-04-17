# MailApplication
The Masai Mail Application is a secure email service that allows users to send and receive emails, with features such as multiple recipients, starring and deleting emails, and user authentication.


# Tech Stack
- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger UI
- Lambok
- Maven


Features
- Users can send and receive emails
- Users can send emails to multiple recipients
- Users can star and delete emails
- Users can register and login to the application
- Passwords are securely hashed and stored in the database
- Global exception handling is implemented to handle exceptions in a single place
- Input validation is implemented to ensure the data entered by users is in the correct format
- Authentication is implemented using Spring Security to verify user credentials


# Modules

- Login Module
- User Module

- Email Module
- Security Module







# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
      server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/MasaiMailDB;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

# API Root Endpoint
```
https://localhost:8080/
```
```
http://localhost:8080/swagger-ui/
```
