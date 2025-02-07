# Library Management System
A **Java-based** Library Management System with a **Spring Boot backend** and a **JavaFX frontend**, secured using **JWT-based authentication**.

[//]: # (`Backend built with Spring Boot, frontend with JavaFX, and secured with Spring Security &#40;JWT&#41;`)

## 1. Prerequisites ##
Ensure the following dependencies are installed before running the project:

- **Java 17+**
- **JavaFX 21**
- **Spring Boot 3.4.2**
- **Maven 3.8+**
- **H2 Database** (Embedded, no separate installation needed)
- **JWT (JSON Web Tokens)**

---

## 2. Project Modules ##
The system is divided into two modules:

- book-api (Spring Boot Backend)
- book-ui  (JavaFX Frontend)

---

## 3. Running the Backend (Spring Boot) ##

Follow these steps to set up and run the backend service:

- **Clone the repository:**
  `git clone https://github.com/musty-codified/library-management.git`
- **Navigate to the book-api project::**
  `cd library-mgt-system/book-api`
- **Configure environment:** Update `application.yml` with your H2 Database configurations.
- **Build and run the backend project using maven:**

  `mvn clean install`

  `mvn spring-boot:run`
- The backend will start on **`http://localhost:8000`**.

---

## 4. API Documentation ##
The REST API endpoints are prefixed with `/library-app-ws/api/v1` due to the context-path setting in the properties file. 

### 4.1 User Authentication APIs ###

- (POST) [Register  User](http://localhost:8000/library-app-ws/api/v1/users) `/library-app-ws/api/v1/users`
- (POST) [User Login](http://localhost:8000/library-app-ws/api/v1/auth/login) `/library-app-ws/api/v1/auth/login`

### 4.2 Book Management APIs ###

- (POST) [Add a Book](http://localhost:8000/library-app-ws/api/v1/books) `/library-app-ws/api/v1/books`
- (GET)  [Fetch all Books](http://localhost:8000/library-app-ws/api/v1/books)  `/library-app-ws/api/v1/books?pageNumber=1&pageSize=2&searchText=Hitchhiker`
- (DELETE) [Delete a Book](http://localhost:8000/library-app-ws/api/v1/books/{id}) `/library-app-ws/api/v1/books/1`
- (PUT) [Update book Details](http://localhost:8000/library-app-ws/api/v1/books/{id}) `/library-app-ws/api/v1/books/1`

### 4.3 Book Transactions APIs ###

- (POST) [Borrow a Book](http://localhost:8000/library-app-ws/api/v1/books/{id}/{email}) `/library-app-ws/api/v1/books/1/musty@gmail.com` 
- (POST) [Return a Book](http://localhost:8000/library-app-ws/api/v1/books/{id}) `/library-app-ws/api/v1/books/1`

---

## 5. Running the Frontend (JavaFX) ##

- **Navigate to the frontend module**
- **Ensure JavaFX SDK is installed on your machine before running this project**
-  JavaFX is required to run the frontend. You can download it from:
  [https://openjfx.io/](https://openjfx.io/)
  `cd book-ui`
- **Build and run the frontend project using maven:**

  `mvn clean install`

  `mvn javafx:run`


## 6. Additional Notes

### 6.1 Database Access
The H2 database can be accessed via:
- **URL:** `http://localhost:8000/h2-console`
- **JDBC URL:** `jdbc:h2:mem:test_db`

### 6.2 API Authentication
- Most API endpoints require a **JWT token** for access.
- Obtain a token via the **User Login API** and pass it in the request header:
  ```sh
  Authorization: Bearer <your-token-here>
  ```

---

## 7. Common Issues & Debugging

### 7.1 Backend Port Already in Use
If port `8000` is occupied, change the port in `application.yml`:
```yaml
server:
  port: 8081
```

---











