package org.youngmonkeys.sr.v1.service;

import java.util.Optional;

import org.youngmonkeys.sr.v1.model.Authentication;
import org.youngmonkeys.sr.v1.model.CustomUserDetails;
import org.youngmonkeys.sr.v1.model.RefreshToken;
import org.youngmonkeys.sr.v1.request.LoginRequest;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    public Optional<Authentication> authenticateUser(String token) {
        return Optional.of(new Authentication());
    }

    public Optional<Authentication> authenticateUser(LoginRequest loginRequest) {
        return Optional.of(new Authentication());
    }

    public Optional<RefreshToken> createAndPersistRefreshTokenForDevice(
        Authentication authentication,
        LoginRequest loginRequest
    ) {
        return Optional.of(new RefreshToken());
    }

    public String generateToken(CustomUserDetails customUserDetails) {
        return "token";
    }
}
