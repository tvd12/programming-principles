package org.youngmonkeys.code_smell.converter;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.code_smell.model.SaveUserModel;
import org.youngmonkeys.code_smell.request.AddUserRequest;

@EzySingleton
public class RequestToModelConverter {

    public SaveUserModel toModel(
        AddUserRequest request
    ) {
        return SaveUserModel.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .displayName(request.getDisplayName())
            .email(request.getEmail())
            .build();
    }
}
