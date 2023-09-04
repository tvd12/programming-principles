package org.youngmonkeys.sr.v2.service;

import org.youngmonkeys.sr.v1.model.Authentication;
import org.youngmonkeys.sr.v2.model.Token;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class TokenService {

    public Token createUserToken(Authentication authentication) {
        return new Token();
    }
}
