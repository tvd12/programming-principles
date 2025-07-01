package org.youngmonkeys.copilot.example.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import lombok.AllArgsConstructor;
import org.youngmonkeys.copilot.example.response.LoginResponse;
import org.youngmonkeys.copilot.example.service.AuthenticationService;

@Controller("/api/v1")
@AllArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @DoPost("/login")
    public LoginResponse login(
        String username,
        String password
    ) {
        return LoginResponse.builder()
            .accessToken(
                authenticationService.authenticate(username, password)
            )
            .build();
    }
}
