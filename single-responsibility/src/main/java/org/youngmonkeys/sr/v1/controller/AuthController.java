package org.youngmonkeys.sr.v1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youngmonkeys.sr.v1.exception.UserAuthException;
import org.youngmonkeys.sr.v1.exception.UserLoginException;
import org.youngmonkeys.sr.v1.model.Authentication;
import org.youngmonkeys.sr.v1.model.CustomUserDetails;
import org.youngmonkeys.sr.v1.model.RefreshToken;
import org.youngmonkeys.sr.v1.model.User;
import org.youngmonkeys.sr.v1.provider.TokenProvider;
import org.youngmonkeys.sr.v1.request.LoginRequest;
import org.youngmonkeys.sr.v1.response.JwtAuthenticationResponse;
import org.youngmonkeys.sr.v1.security.SecurityContextHolder;
import org.youngmonkeys.sr.v1.service.AuthService;
import org.youngmonkeys.sr.v1.service.UserService;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.ezyhttp.server.core.annotation.RequestCookie;

import lombok.AllArgsConstructor;

@Controller("/api/v1")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @DoGet("/users/{username}/profile")
    public ResponseEntity getUserProfile(
        @PathVariable("username") String username,
        @RequestCookie("token") String token
    ) {
        Authentication authentication = authService
            .authenticateUser(token)
            .orElseThrow(
                () -> new UserAuthException(
                    "Couldn't found user [" + username + "]"
                )
            );
        User user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @DoPost("/authenticate")
    public ResponseEntity authenticateUser(
        @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authService
            .authenticateUser(loginRequest)
            .orElseThrow(
                () -> new UserLoginException(
                    "Couldn't login user [" + loginRequest + "]"
                )
            );
        CustomUserDetails customUserDetails =
            authentication.getPrincipal();
        logger.info(
            "Logged in User returned [API]: " +
            customUserDetails.getUsername()
        );
        SecurityContextHolder
            .getContext()
            .setAuthentication(authentication);
        return authService
            .createAndPersistRefreshTokenForDevice(
                authentication,
                loginRequest
            )
            .map(RefreshToken::getToken)
            .map(refreshToken -> {
                String jwtToken = authService.generateToken(
                    customUserDetails
                );
                return ResponseEntity.ok(
                    new JwtAuthenticationResponse(
                        jwtToken,
                        refreshToken,
                        tokenProvider.getExpiryDuration()
                    )
                );
            })
            .orElseThrow(
                () -> new UserLoginException(
                    "Couldn't create refresh token for: [" +
                    loginRequest + "]"
                )
            );
    }
}
