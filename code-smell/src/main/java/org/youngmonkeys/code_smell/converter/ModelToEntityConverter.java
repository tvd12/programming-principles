package org.youngmonkeys.code_smell.converter;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.code_smell.entity.UserEntity;
import org.youngmonkeys.code_smell.model.SaveUserModel;

@EzySingleton
public class ModelToEntityConverter {

    public UserEntity toEntity(SaveUserModel model) {
        UserEntity entity = new UserEntity();
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setDisplayName(model.getDisplayName());
        entity.setEmail(model.getEmail());
        return entity;
    }
}
