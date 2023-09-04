package org.youngmonkeys.sr.v1.model;

public class Authentication {

    public CustomUserDetails getPrincipal() {
        return new CustomUserDetails();
    }
}
