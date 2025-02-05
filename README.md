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

## 2. Project Modules ##

- book-api (Spring Boot Backend)
- book-ui  (JavaFX Frontend)

---

## 3. Running the application locally ##

- **Ensure JavaFX SDK is installed on your machine**
- **Clone the repository:** git clone [repo-link](https://github.com/musty-codified/library-management.git)
- **Navigate to the book-api project::**
    `cd book-ui`
- **Configure environment:** Update `application.yml` with your H2 Database configurations.
- **Build and run the backend project using maven:**
  `mvn clean install`
  `mvn spring-boot:run`

---

## 4. API Documentation ##

- The services expose REST APIs for managing books and transactions as per the task description document (TSD).
- API endpoints are as follows :

### 4.1 User Authentication APIs ###

- (HTTP:POST) [Register  User](http://localhost:8000/library-app-ws/api/v1/users)
- (HTTP:POST) [User Login](http://localhost:8000/library-app-ws/api/v1/auth/login)

### 4.2 Book Management APIs ###

- (HTTP:POST) [Add a Book](http://localhost:8000/library-app-ws/api/v1/books)
- (HTTP:GET)  [Fetch all Books](http://localhost:8000/library-app-ws/api/v1/books)
- (HTTP:DELETE) [Delete a Book](http://localhost:8000/library-app-ws/api/v1/books/{id})
- (HTTP:PUT) [Update book Details](http://localhost:8000/library-app-ws/api/v1/books/{id})

### 4.3 Book Transactions APIs ###

- (HTTP:POST) [Borrow a Book](http://localhost:8000/library-app-ws/api/v1/books/{id}/{email})
- (HTTP:POST) [Return a Book](http://localhost:8000/library-app-ws/api/v1/books/{id})

---

## 5. Running the Frontend (JavaFX) ##

- **Navigate to the book-ui project::**
  `cd book-ui`
- **Build and run the frontend project using maven:**
  `mvn clean install`
  `mvn javafx:run`













