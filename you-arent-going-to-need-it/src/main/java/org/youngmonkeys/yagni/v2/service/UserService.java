package org.youngmonkeys.yagni.v2.service;

import org.youngmonkeys.yagni.v2.model.UpdateUserModel;
import org.youngmonkeys.yagni.v2.model.UserModel;

public class UserService {

    public UserModel getUserById(long userId) {
        return UserModel
            .builder()
            .build();
    }

    public void updateUser(long userId, UpdateUserModel model) {}
}
