# Library Management System

`Backend built with Spring Boot, frontend with JavaFX, and secured with Spring Security (JWT)`

## 1. Project Setup ##
- **Java 17+**
- **JavaFX 21**
- **Spring Boot 3.4.1**
- **Maven 3.8+**
- **H2 Database**
- **JWT**

---

## 2. Project Modules
- book-api
- book-ui

---

## 3. Running the application locally ##
- **Clone the repository:** git clone [repo-link](https://github.com/musty-codified/library-management.git)
- **Configure Environment:** Update `application.yml` with your H2 Database configurations.
- **Build and run the backend project using maven:** mvn clean install
- **Run the backend from the command line:** mvn spring-boot:run

---

## 4. API Documentation ##
- The services expose REST APIs for managing books and transactions as per the task description document (TSD).
- API endpoints are as follows :

- http://localhost:8000/library-app-ws/api/v1/users (HTTP:POST)
- http://localhost:8000/library-app-ws/api/v1/auth/login (HTTP:POST)
- http://localhost:8000/library-app-ws/api/v1/books (HTTP:POST)
- http://localhost:8000/library-app-ws/api/v1/books (HTTP:GET)
- http://localhost:8000/library-app-ws/api/v1/books/{id} (HTTP:DELETE)
- http://localhost:8000/library-app-ws/api/v1/books/{id} (HTTP:PUT)
- http://localhost:8000/library-app-ws/api/v1/books/{id}/{email} (HTTP:POST)
- http://localhost:8000/library-app-ws/api/v1/books/{id} (HTTP:POST)
















