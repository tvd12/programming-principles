package org.youngmonkeys.code_smell.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private String username;
    private String password;
    private String displayName;
    private String email;
}
