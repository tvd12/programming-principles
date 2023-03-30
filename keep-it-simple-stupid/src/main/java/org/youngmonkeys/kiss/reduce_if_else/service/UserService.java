package org.youngmonkeys.kiss.reduce_if_else.service;

import org.youngmonkeys.kiss.reduce_if_else.model.UserModel;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    public UserModel getUserById(long userId) {
        return new UserModel();
    }
}
