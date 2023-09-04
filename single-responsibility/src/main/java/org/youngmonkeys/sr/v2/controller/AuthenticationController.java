package org.youngmonkeys.sr.v2.controller;

import org.youngmonkeys.sr.v1.model.Authentication;
import org.youngmonkeys.sr.v1.request.LoginRequest;
import org.youngmonkeys.sr.v1.response.JwtAuthenticationResponse;
import org.youngmonkeys.sr.v1.security.SecurityContextHolder;
import org.youngmonkeys.sr.v2.converter.ModelToResponseConverter;
import org.youngmonkeys.sr.v2.model.Token;
import org.youngmonkeys.sr.v2.service.AuthenticationService;
import org.youngmonkeys.sr.v2.service.TokenService;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Controller("/api/v1")
@AllArgsConstructor
public class AuthenticationController extends EzyLoggable {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private ModelToResponseConverter modelToResponseConverter;

    @DoPost("/authenticate")
    public JwtAuthenticationResponse authenticateUser(
        @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authenticationService
            .authenticateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            );
        logger.info(
            "Logged in User returned [API]: {}",
            authentication.getPrincipal().getUsername()
        );
        SecurityContextHolder
            .getContext()
            .setAuthentication(authentication);
        Token token = tokenService.createUserToken(authentication);
        return modelToResponseConverter.toJwtResponse(token);
    }
}
