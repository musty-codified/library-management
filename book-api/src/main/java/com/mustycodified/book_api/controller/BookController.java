package com.mustycodified.book_api.controller;


import com.mustycodified.book_api.dto.request.BookRequestDto;
import com.mustycodified.book_api.dto.response.ApiResponse;
import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<ApiResponse.Wrapper<List<BookResponseDto>>>> fetchAllBooks(
            @RequestParam(required = false) String searchText,
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "desc", required = false) String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return ResponseEntity.ok(new ApiResponse<>(true, "Request successfully processed", bookService.getAllBooks(searchText, pageable)));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> getBooks() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Request successfully processed", bookService.getBooks()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> updateBook(@PathVariable(name = "id") Long id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto responseDto = bookService.editBook(id, bookRequestDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Request successfully processed", responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/{email}")
    public ResponseEntity<ApiResponse<BookResponseDto>> borrowBook(@PathVariable (name = "id") Long id,
                                                                   @PathVariable(name = "email") String email){
        return ResponseEntity.ok().body( new ApiResponse<>(true, "Request successfully processed", bookService.borrowBook(id, email)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> returnBook( @PathVariable Long id){
        return ResponseEntity.ok().body( new ApiResponse<>(true, "Request successfully processed", bookService.returnBook(id)));
    }
}
