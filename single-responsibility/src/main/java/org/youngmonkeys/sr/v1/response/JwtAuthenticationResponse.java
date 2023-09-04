package org.youngmonkeys.sr.v1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String jwtToken;
    private String refreshToken;
    private long expiryDuration;
}
