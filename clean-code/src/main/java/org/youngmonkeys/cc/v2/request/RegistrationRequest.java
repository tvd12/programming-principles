package org.youngmonkeys.cc.v2.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
    private boolean registerAsAdmin;
}