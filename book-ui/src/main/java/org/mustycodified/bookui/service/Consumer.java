package org.mustycodified.bookui.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mustycodified.bookui.model.request.UserLoginRequestModel;
import org.mustycodified.bookui.model.response.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {

    private static final String BASE_URL = "http://localhost:8000/library-app-ws/api/v1";
    private static String AUTH_TOKEN = "";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Log log = LogFactory.getLog(Consumer.class);

    public static boolean loginUser(UserLoginRequestModel login) {
        String loginUrl = String.format("%s/auth/login", BASE_URL);
        // Prepare request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Send request
        HttpEntity<UserLoginRequestModel> request = new HttpEntity<>(login, headers);
        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplate.exchange(loginUrl, HttpMethod.POST, request, Map.class).getBody();
        LoginResponse loginResponse = parseResponse(responseMap);
        AUTH_TOKEN = loginResponse.getAccessToken();
        return Boolean.TRUE.equals(responseMap.get("success"));
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        log.info(AUTH_TOKEN);
        httpHeaders.add("Authorization", "Bearer " + AUTH_TOKEN);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private static LoginResponse parseResponse(Map<String, Object> response) {
        if (response == null || !Boolean.TRUE.equals(response.get("success"))) {
            throw new IllegalStateException("Failed to LOGIN");
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        if (data == null) {
            throw new IllegalStateException("Missing data");
        }

        // Extract raw values from the response map
        Long id = ((Number) data.get("id")).longValue();
        String firstName = (String) data.get("firstName");
        String lastName = (String) data.get("lastName");
        String accessToken = (String) data.get("accessToken");
        Long expiresIn = ((Number) data.get("expiresIn")).longValue();

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(id);
        loginResponse.setFirstName(firstName);
        loginResponse.setLastName(lastName);
        loginResponse.setAccessToken(accessToken);
        loginResponse.setExpiresIn(expiresIn);

        return loginResponse;
    }

    public static void clearAuthToken() {
        AUTH_TOKEN = "";
        System.out.println("Authentication token cleared.");
    }

}
