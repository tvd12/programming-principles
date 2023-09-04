package org.youngmonkeys.sr.v2.service;

import org.youngmonkeys.sr.v1.model.Authentication;

public class AuthenticationService {

    public Authentication authenticateUser(
        String email,
        String password
    ) {
        return new Authentication();
    }
}
