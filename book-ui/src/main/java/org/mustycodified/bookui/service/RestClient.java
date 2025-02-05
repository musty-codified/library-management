package org.mustycodified.bookui.service;
import org.mustycodified.bookui.model.ApiResponse;
import org.mustycodified.bookui.model.request.Book;
import org.mustycodified.bookui.model.request.Login;
import org.mustycodified.bookui.model.request.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mustycodified.bookui.service.ProxyClient.getHttpHeaders;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8000/library-app-ws/api/v1";
    private static final RestTemplate restTemplate = new RestTemplate();

    static HttpEntity<Book> entity ;

    public static List<Book> fetchBooks() {
        entity = new HttpEntity<>(getHttpHeaders());
        ApiResponse response  = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, ApiResponse.class).getBody();
        Book[] books = (Book[]) Objects.requireNonNull(response).getData();
        return books != null ? Arrays.asList(books) : List.of();
    }

    public static void addBook(Book book) {
        entity  = new HttpEntity<>(getHttpHeaders());
      ApiResponse response =  restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, ApiResponse.class).getBody();
      Objects.requireNonNull(response).getData();
    }

    public static void updateBook(Book book) {
        Objects.requireNonNull(restTemplate.exchange(BASE_URL + "/" + book.getId(), HttpMethod.PUT, entity, ApiResponse.class).getBody()).getData();

    }
    public static void deleteBook(Long id) {
        Objects.requireNonNull(restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, entity, ApiResponse.class).getBody()).getData();
    }

    public static void registerUser(User user) {
        entity  = new HttpEntity<>(getHttpHeaders());
        ApiResponse response =  restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, ApiResponse.class).getBody();
        Objects.requireNonNull(response).getData();
    }

    public static void loginUser(Login login) {
        entity  = new HttpEntity<>(getHttpHeaders());
        ApiResponse response =  restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, ApiResponse.class).getBody();
        Objects.requireNonNull(response).getData();
    }
}

