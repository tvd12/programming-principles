package org.youngmonkeys.sr.v1.security;

public class SecurityContextHolder {

    public static SecurityContext getContext() {
        return new SecurityContext();
    }
}
