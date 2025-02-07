# Library Management System

`Backend built with Spring Boot, frontend with JavaFX, and secured with Spring Security (JWT)`

## 1. Prerequisites ##

- **Java 17+**
- **JavaFX 21**
- **Spring Boot 3.4.2**
- **Maven 3.8+**
- **H2 Database**
- **JWT**

---

## 2. Project Modules ##

- book-api (Spring Boot Backend)
- book-ui  (JavaFX Frontend)

---

## 3. Running the Backend (Spring Boot) ##

- **Ensure JDK 17+ is installed on your machine**
- **Clone the repository:** git clone https://github.com/musty-codified/library-management.git
- **Navigate to the book-api project::**
    `cd book-api`
- **Configure environment:** Update `application.yml` with your H2 Database configurations.
- **Build and run the backend project using maven:**
  
  `mvn clean install`
 
  `mvn spring-boot:run`

---

## 4. API Documentation ##

- The service expose REST APIs for managing books as per the task description document (TSD).
- I have included user Authorization APIs and Transaction management
- The API endpoints are as follows :

### 4.1 User Authentication APIs ###

- (POST) [Register  User](http://localhost:8000/library-app-ws/api/v1/users)
- (POST) [User Login](http://localhost:8000/library-app-ws/api/v1/auth/login)

### 4.2 Book Management APIs ###

- (POST) [Add a Book](http://localhost:8000/library-app-ws/api/v1/books)
- (GET)  [Fetch all Books with optional filtering and pagination](http://localhost:8000/library-app-ws/api/v1/books)
- (DELETE) [Delete a Book](http://localhost:8000/library-app-ws/api/v1/books/{id})
- (PUT) [Update book Details](http://localhost:8000/library-app-ws/api/v1/books/{id})

### 4.3 Book Transactions APIs ###

- (POST) [Borrow a Book](http://localhost:8000/library-app-ws/api/v1/books/{id}/{email})
- (POST) [Return a Book](http://localhost:8000/library-app-ws/api/v1/books/{id})

---

## 5. Running the Frontend (JavaFX) ##
- **Ensure JavaFX SDK is installed on your machine before running this project**
- **Navigate to the book-ui project::**
  `cd book-ui`
- **Build and run the frontend project using maven:**
  
  `mvn clean install`

  `mvn javafx:run`













