package org.youngmonkeys.sr.v2.service;

import org.youngmonkeys.sr.v1.model.User;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    public String validateUserAccessToken(String accessToken) {
        return "";
    }

    public User getUserByName(String username) {
        return new User();
    }

    public void updateUserAvatar(
        String username,
        long newAvatarId
    ) {}
}
