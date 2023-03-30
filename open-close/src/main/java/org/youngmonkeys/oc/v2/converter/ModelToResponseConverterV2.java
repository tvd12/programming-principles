package org.youngmonkeys.oc.v2.converter;

import org.youngmonkeys.oc.v1.model.UserModel;
import org.youngmonkeys.oc.v2.response.UserResponseV2;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ModelToResponseConverterV2 {

    public UserResponseV2 toResponse(UserModel model) {
        return UserResponseV2
            .builder()
            .displayName(model.getNickName())
            .avatarUrl(model.getAvatarUrl())
            .build();
    }
}
