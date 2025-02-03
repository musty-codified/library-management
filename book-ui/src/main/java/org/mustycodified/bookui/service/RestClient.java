package org.mustycodified.bookui.service;

import org.mustycodified.bookui.model.ApiResponse;
import org.mustycodified.bookui.model.Book;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8000/library-app-ws/api/v1/books";
    private static final String AUTH_TOKEN = "";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + AUTH_TOKEN);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    static HttpEntity<Book> entity ;

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
}

