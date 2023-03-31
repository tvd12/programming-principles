package org.youngmonkeys.yagni.v1.converter;

import org.youngmonkeys.yagni.v2.model.UpdateUserModel;
import org.youngmonkeys.yagni.v2.request.UpdateUserRequest;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class RequestToModelConverter {

    public UpdateUserModel toModel(UpdateUserRequest request) {
        return UpdateUserModel
            .builder()
            .displayName(request.getDisplayName())
            .avatarUrl(request.getAvatarUrl())
            .build();
    }
}
