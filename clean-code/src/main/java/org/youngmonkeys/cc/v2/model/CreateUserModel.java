package org.youngmonkeys.cc.v2.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserModel {
    private String username;
    private String email;
    private String password;
}