package org.youngmonkeys.code_smell.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
    private String displayName;
    private String email;
}
