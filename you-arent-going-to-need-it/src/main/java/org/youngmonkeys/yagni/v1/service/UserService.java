package org.youngmonkeys.yagni.v1.service;

import org.youngmonkeys.yagni.v1.dto.UserDTO;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    public UserDTO getUserById(long userId) {
        return new UserDTO();
    }

    public void updateUser(UserDTO user) {}
}
