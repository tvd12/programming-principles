package org.youngmonkeys.oc.v1.converter;

import org.youngmonkeys.oc.v1.model.UserModel;
import org.youngmonkeys.oc.v1.response.UserResponse;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ModelToResponseConverter {

    public UserResponse toResponse(UserModel model) {
        return UserResponse
            .builder()
            .username(model.getUsername())
            .email(model.getEmail())
            .fullName(model.getFullName())
            .phoneNumber(model.getPhoneNumber())
            .birthDay(model.getBirthDay())
            .build();
    }

    public UserResponse toResponse(String apiVersion, UserModel model) {
        if (apiVersion.equals("v1")) {
            return UserResponse
                .builder()
                .username(model.getUsername())
                .email(model.getEmail())
                .fullName(model.getFullName())
                .phoneNumber(model.getPhoneNumber())
                .birthDay(model.getBirthDay())
                .build();
        } else {
            return UserResponse
                .builder()
                .email(model.getEmail())
                .displayName(model.getNickName())
                .avatarUrl(model.getAvatarUrl())
                .build();
        }
    }
}
