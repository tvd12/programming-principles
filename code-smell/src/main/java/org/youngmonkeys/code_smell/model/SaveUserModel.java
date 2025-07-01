package org.youngmonkeys.code_smell.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveUserModel {
    private final String username;
    private final String password;
    private final String displayName;
    private final String email;
}
