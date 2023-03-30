package org.youngmonkeys.wdc.duplicated_initiation.v2.converter;

import org.youngmonkeys.wdc.duplicated_initiation.v1.model.UserModel;
import org.youngmonkeys.wdc.duplicated_initiation.v1.response.UserResponse;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ModelToResponseConverter {

    public UserResponse toResponse(UserModel model) {
        return UserResponse
            .builder()
            .uuid(model.getUuid())
            .displayName(model.getDisplayName())
            .profileUrl(model.getProfileUrl())
            .build();
    }
}
