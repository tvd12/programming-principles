package org.youngmonkeys.code_smell.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveUserModel {
    private String username;
    private String password;
    private String displayName;
    private String email;
}
