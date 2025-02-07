package org.mustycodified.bookui.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mustycodified.bookui.model.request.Book;
import org.mustycodified.bookui.model.request.User;
import org.mustycodified.bookui.model.response.BookResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.mustycodified.bookui.service.ProxyClient.getHttpHeaders;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8000/library-app-ws/api/v1";
    private static final RestTemplate restTemplate = new RestTemplate();

    static HttpEntity<Book> entity ;

    public static List<BookResponse> fetchBooks() {
        String fetchUrl = String.format("%s/books/list", BASE_URL);
        entity = new HttpEntity<>(getHttpHeaders());
        String jsonString = restTemplate.exchange(fetchUrl, HttpMethod.GET, entity, String.class).getBody();
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

    public static List<BookResponse> searchBooks(String searchText) {
        String searchUrl = String.format("%s/books?searchText=" + (searchText != null ? searchText:""),  BASE_URL);
        System.out.println(searchUrl);
        entity = new HttpEntity<>(getHttpHeaders());
        String jsonString = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseMap = null;
        Map<String, Object> dataMap = null;
        List<Map<String, Object>> mapList = null;
        try {
            responseMap = mapper.readValue(jsonString, new TypeReference<>(){});
            dataMap = (Map<String, Object>)responseMap.get("data");
            mapList = (List<Map<String, Object>>) dataMap.get("content");

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
        String addBookUrl = String.format("%s/books", BASE_URL);
        entity  = new HttpEntity<>(book, getHttpHeaders());
        restTemplate.exchange(addBookUrl, HttpMethod.POST, entity, String.class).getBody();
    }

    public static void updateBook(Book book) {
        String updateBookUrl = String.format("%s/books/%s", BASE_URL, book.getId());
        entity  = new HttpEntity<>(book, getHttpHeaders());
        restTemplate.exchange(updateBookUrl, HttpMethod.PUT, entity, String.class).getBody();

    }
    public static void deleteBook(Long id) {
        String deleteBookUrl = String.format("%s/books/%s", BASE_URL, id);
        entity  = new HttpEntity<>(getHttpHeaders());
        restTemplate.exchange(deleteBookUrl, HttpMethod.DELETE, entity, String.class).getBody();
    }

    public static void registerUser(User user) {
        String registerUrl = String.format("%s/users", BASE_URL);
        // Prepare request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Send request
        HttpEntity<User> request = new HttpEntity<>(user, headers);
         restTemplate.exchange(registerUrl, HttpMethod.POST, request, String.class).getBody();
    }

}

