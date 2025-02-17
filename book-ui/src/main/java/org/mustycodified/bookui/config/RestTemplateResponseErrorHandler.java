package org.mustycodified.bookui.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
            return httpResponse.getStatusCode().is5xxServerError() ||
                    httpResponse.getStatusCode().is4xxClientError();
        }

        @Override
        public void handleError(ClientHttpResponse httpResponse) throws IOException {
            if (httpResponse.getStatusCode().is5xxServerError()) {
                //Handle SERVER_ERROR
                throw new HttpServerErrorException(httpResponse.getStatusCode());
            } else if (httpResponse.getStatusCode().is4xxClientError()) {
                //Handle CLIENT_ERROR
                throw new HttpClientErrorException(httpResponse.getStatusCode(), "Client error occurred");


            }
        }
    }

