package org.youngmonkeys.cc.v2.converter;

import org.youngmonkeys.cc.v2.entity.User;
import org.youngmonkeys.cc.v2.model.CreateUserModel;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.security.EzySHA256;

@EzySingleton
public class ModelToEntityConverter {

    public User toEntity(CreateUserModel model) {
        User entity = new User();
        entity.setEmail(model.getEmail());
        entity.setPassword(EzySHA256.cryptUtf(model.getPassword()));
        entity.setUsername(model.getUsername());
        entity.setActive(true);
        entity.setEmailVerified(false);
        return entity;
    }
}
