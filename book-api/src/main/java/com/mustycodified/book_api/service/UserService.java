package com.mustycodified.book_api.service;

import com.mustycodified.book_api.dto.request.LoginRequestDto;
import com.mustycodified.book_api.dto.request.UserRequestDto;
import com.mustycodified.book_api.dto.response.LoginResponseDto;
import com.mustycodified.book_api.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    LoginResponseDto login (LoginRequestDto adminLoginRequestDto, HttpServletRequest request);

}
