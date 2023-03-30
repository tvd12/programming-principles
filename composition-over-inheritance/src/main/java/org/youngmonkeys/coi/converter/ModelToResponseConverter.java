package org.youngmonkeys.coi.converter;

import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.response.UserResponse;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ModelToResponseConverter {

    public UserResponse toResponse(
        UserModel model
    ) {
        return UserResponse
            .builder()
            .id(model.getId())
            .name(model.getName())
            .build();
    }

    public UserResponse toResponse(
        UserModel model,
        String phoneNumber
    ) {
        return UserResponse
            .builder()
            .id(model.getId())
            .name(model.getName())
            .phoneNumber(phoneNumber)
            .build();
    }
}
