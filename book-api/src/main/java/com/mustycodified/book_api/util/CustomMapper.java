package com.mustycodified.book_api.util;

import com.mustycodified.book_api.dto.response.BookResponseDto;
import com.mustycodified.book_api.dto.response.UserResponseDto;
import com.mustycodified.book_api.entity.Book;
import com.mustycodified.book_api.entity.User;
import com.mustycodified.book_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomMapper {
    private final UserRepository userRepository;

    public UserResponseDto mapToDto(User userEntity) {
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    public BookResponseDto mapToDto(Book bookEntity) {
        return BookResponseDto.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .quantity(bookEntity.getQuantity())
                .build();
    }

}
