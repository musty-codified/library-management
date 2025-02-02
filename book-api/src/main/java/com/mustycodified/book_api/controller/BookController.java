package com.mustycodified.book_api.controller;


import com.mustycodified.book_api.dto.request.BookRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponseDto>> addBook(@Valid @RequestBody BookRequestDto requestDto) {
        BookResponseDto responseDto = bookService.addBook(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ApiResponse<>(true, "Request successfully processed", responseDto));
    }


}
