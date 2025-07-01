package org.youngmonkeys.copilot.example.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class AuthenticationService {

    public String authenticate(String username, String password) {
        return "mocked-access-token-for-" + username;
    }
}
