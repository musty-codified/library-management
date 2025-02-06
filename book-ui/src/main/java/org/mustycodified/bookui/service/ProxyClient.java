package org.mustycodified.bookui.service;


import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mustycodified.bookui.model.request.Login;
import org.mustycodified.bookui.model.response.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class ProxyClient {

    private static final String BASE_URL = "http://localhost:8000/library-app-ws/api/v1";
    private static String AUTH_TOKEN = "";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Log log = LogFactory.getLog(ProxyClient.class);

    public static boolean loginUser(Login login) {
        String loginUrl = BASE_URL + "/auth/login";

        // Prepare request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Send request
        HttpEntity<Login> request = new HttpEntity<>(login, headers);
        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplate.exchange(loginUrl, HttpMethod.POST, request, Map.class).getBody();
        LoginResponse loginResponse = parseResponse(responseMap);
        AUTH_TOKEN = loginResponse.getAccessToken();
        System.out.println(AUTH_TOKEN);
        return Boolean.TRUE.equals(responseMap.get("success"));
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
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


}
