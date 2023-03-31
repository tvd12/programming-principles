package org.youngmonkeys.yagni.v1.converter;

import org.youngmonkeys.yagni.v2.model.UserModel;
import org.youngmonkeys.yagni.v2.response.UserResponse;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ModelToResponseConverter {

    public UserResponse toResponse(UserModel model) {
        return UserResponse
            .builder()
            .userId(model.getUserId())
            .displayName(model.getDisplayName())
            .avatarUrl(model.getAvatarUrl())
            .build();
    }
}
