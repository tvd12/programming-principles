package org.youngmonkeys.code_smell;

import com.tvd12.ezyfox.security.EzySHA256;
import org.youngmonkeys.code_smell.entity.UserEntity;
import org.youngmonkeys.code_smell.model.SaveUserModel;

public class DistanceTooFar {

    public UserEntity toEntity(SaveUserModel model) {
        String encryptedPassword = EzySHA256.cryptUtfToLowercase(
            model.getPassword()
        );
        UserEntity entity = new UserEntity();

        // set username, if username is null or empty,
        // throw an exception
        entity.setUsername(model.getUsername());

        // set display name if display name is null or empty,
        // throw an exception
        entity.setDisplayName(model.getDisplayName());

        // set email it can be null or empty
        entity.setEmail(model.getEmail());

        // set password, it will be encrypted
        entity.setPassword(encryptedPassword);
        return entity;
    }
}
