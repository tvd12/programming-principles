package org.youngmonkeys.kiss.simplify_unit_testing.converter;

import org.youngmonkeys.kiss.simplify_unit_testing.entity.User;
import org.youngmonkeys.kiss.simplify_unit_testing.model.UserModel;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class EntityToModelConverter {

    public UserModel toModel(User entity) {
        return UserModel
            .builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .build();
    }
}
