package org.mustycodified.bookui.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mustycodified.bookui.model.ApiResponse;
import org.mustycodified.bookui.model.request.Book;
import org.mustycodified.bookui.model.request.Login;
import org.mustycodified.bookui.model.request.User;
import org.mustycodified.bookui.model.response.BookResponse;
import org.mustycodified.bookui.model.response.LoginResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.mustycodified.bookui.service.ProxyClient.getHttpHeaders;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8000/library-app-ws/api/v1";
    private static final RestTemplate restTemplate = new RestTemplate();

    static HttpEntity<Book> entity ;

    public static List<BookResponse> fetchBooks() {
        entity = new HttpEntity<>(getHttpHeaders());
        String jsonString = restTemplate.exchange(BASE_URL + "/books/list", HttpMethod.GET, entity, String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseMap = null;
        List<Map<String, Object>> mapList=null;
        try {
            responseMap = mapper.readValue(jsonString, new TypeReference<>(){});
            mapList = (List<Map<String, Object>>) responseMap.get("data");

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(parseBook(mapList));
        return parseBook(mapList);
    }

    private static List<BookResponse> parseBook(List<Map<String, Object>> mapList) {
        List<BookResponse> bookList = new ArrayList<>();

        for (Map<String, Object> map : mapList) {
            BookResponse book = new BookResponse();
            book.setId((Integer) map.get("id"));
            book.setTitle((String) map.get("title"));
            book.setAuthor((String) map.get("author"));
            book.setQuantity((Integer) map.get("quantity"));
            bookList.add(book);
        }

        return bookList;

    }

    public static void addBook(Book book) {
        entity  = new HttpEntity<>(book, getHttpHeaders());
        restTemplate.exchange(BASE_URL + "/books", HttpMethod.POST, entity, String.class).getBody();
    }

    public static void updateBook(Book book) {
        entity  = new HttpEntity<>(book, getHttpHeaders());
        restTemplate.exchange(BASE_URL + "/books" + book.getId(), HttpMethod.PUT, entity, String.class).getBody();

    }
    public static void deleteBook(Long id) {
        restTemplate.exchange(BASE_URL + "/books" + id, HttpMethod.DELETE, entity, String.class).getBody();
    }

    public static void registerUser(User user) {
        String registerUrl = BASE_URL + "/users";

        // Prepare request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Send request
        HttpEntity<User> request = new HttpEntity<>(user, headers);
         restTemplate.exchange(registerUrl, HttpMethod.POST, request, String.class).getBody();
    }

}

