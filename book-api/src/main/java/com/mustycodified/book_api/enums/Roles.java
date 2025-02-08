package com.mustycodified.book_api.enums;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

import static com.mustycodified.book_api.enums.UserPermissions.*;

@Getter
public enum Roles {
    USER(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(USER_DELETE, USER_READ, USER_EDIT));
    private final Set<UserPermissions> permissions;

    Roles(Set<UserPermissions> permissions){
        this.permissions = permissions;
    }
}
