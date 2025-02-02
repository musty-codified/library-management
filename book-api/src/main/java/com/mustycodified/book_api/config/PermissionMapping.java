package com.mustycodified.book_api.config;

import lombok.Data;

import java.util.List;

@Data
public class PermissionMapping {
    private String permission;
    private List<String> methods;
    private List<String> patterns;
}

