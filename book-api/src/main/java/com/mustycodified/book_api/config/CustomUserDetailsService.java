package com.mustycodified.book_api.config;

import com.mustycodified.book_api.entity.User;
import com.mustycodified.book_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Arrays.stream(user.getRoles().split(","))
                        .map(String::trim)
                        .map(this::mapRoleToPermission)
                        .flatMap(List::stream)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }

    private List<String> mapRoleToPermission(String role) {
        return switch (role){
            case "USER_READ" -> List.of("user.read");
            case "USER_EDIT" -> List.of("user.edit");
            case "USER_DELETE" -> List.of("user.delete");
            default -> List.of();
        };

    }
}
