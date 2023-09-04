package org.youngmonkeys.sr.v1.service;

import org.youngmonkeys.sr.v1.model.User;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    public User getUser(String username) {
        return new User();
    }
}
