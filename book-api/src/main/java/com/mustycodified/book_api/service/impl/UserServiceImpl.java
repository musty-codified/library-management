package com.mustycodified.book_api.service.impl;

import com.mustycodified.book_api.config.CustomUserDetailsService;
import com.mustycodified.book_api.config.JwtUtils;
import com.mustycodified.book_api.dto.request.LoginRequestDto;
import com.mustycodified.book_api.dto.request.UserRequestDto;
import com.mustycodified.book_api.dto.response.LoginResponseDto;
import com.mustycodified.book_api.dto.response.UserResponseDto;
import com.mustycodified.book_api.entity.User;
import com.mustycodified.book_api.enums.Roles;
import com.mustycodified.book_api.exception.CustomValidationException;
import com.mustycodified.book_api.exception.ResourceAlreadyExistException;
import com.mustycodified.book_api.repository.UserRepository;
import com.mustycodified.book_api.service.UserService;
import com.mustycodified.book_api.util.AppUtil;
import com.mustycodified.book_api.util.CustomMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AppUtil appUtil;
    private final CustomMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        validateEmail(requestDto.getEmail());
        appUtil.validateEmailDomain(requestDto.getEmail());
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new ResourceAlreadyExistException("User already exists");
        }
        if (validatePhoneNumber(appUtil.getFormattedNumber(requestDto.getPhoneNumber()))) {
            throw new CustomValidationException("Invalid phone number {" + requestDto.getPhoneNumber() + "}");
        }

       User newUser = appUtil.getMapper().convertValue(requestDto, User.class);
        newUser.setRoles(Roles.USER.getPermissions().stream()
                .map(Objects::toString).collect(Collectors.joining(",")));
        newUser.setPhoneNumber(appUtil.getFormattedNumber(requestDto.getPhoneNumber()));
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        System.out.println(newUser.getPhoneNumber());

        newUser = userRepository.save(newUser);
        return mapper.mapToDto(newUser);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto adminLoginRequestDto, HttpServletRequest request) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(adminLoginRequestDto.getEmail());
            if (userOptional.isEmpty()) {
                log.error("User not found with email {}", adminLoginRequestDto.getEmail());
                throw new BadCredentialsException("Invalid credentials");
            }

            User user = userOptional.get();
            if (!passwordEncoder.matches(adminLoginRequestDto.getPassword(), user.getPassword())){
                log.error("Bad credentials with email {}", adminLoginRequestDto.getEmail());
                throw new BadCredentialsException("Invalid credentials");
            }

            if (user.getRoles() == null) {
                throw new IllegalArgumentException("Not assigned to any role; cannot log in");
            }
            final String accessToken = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getEmail()));
            return LoginResponseDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .accessToken(accessToken)
                    .expiresIn(jwtExpiration)
                    .build();

        } catch (RuntimeException e){
            throw new com.mustycodified.book_api.exception.BadCredentialsException("Incorrect credentials");

        }
    }


    private void validateEmail(String email) {
        if (!AppUtil.isEmailValid(email)) {
            throw new CustomValidationException("Invalid email format {" + email + "}");
        }
    }

    private boolean validatePhoneNumber(String formattedNumber) {
        return userRepository.existsByPhoneNumber(formattedNumber);
    }
}
