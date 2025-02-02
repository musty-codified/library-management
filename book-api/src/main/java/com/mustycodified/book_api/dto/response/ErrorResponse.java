package com.mustycodified.book_api.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Setter
@Getter
public class ErrorResponse extends ApiResponse<String>{
    public ErrorResponse(String message) {
        super(false, message, Strings.EMPTY);
    }

}
