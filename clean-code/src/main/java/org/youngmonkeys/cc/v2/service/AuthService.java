package org.youngmonkeys.cc.v2.service;

import org.youngmonkeys.cc.v2.model.CreateUserModel;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;

    public void registerUser(
        CreateUserModel model,
        boolean asAdmin
    ) {
        userService.createUser(model, asAdmin);
    }
}