package com.mustycodified.book_api.controller;

import com.mustycodified.book_api.dto.request.UserRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.UserResponseDto;
import com.mustycodified.book_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = userService.createUser(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ApiResponse<>(true, "Request successfully processed", responseDto));

    }
}
