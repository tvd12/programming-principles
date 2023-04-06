package org.youngmonkeys.cc.v2.converter;

import org.youngmonkeys.cc.v2.model.CreateUserModel;
import org.youngmonkeys.cc.v2.request.RegistrationRequest;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class RequestToModelConverter {

    public CreateUserModel toModel(
        RegistrationRequest request
    ) {
        return CreateUserModel
            .builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(request.getPassword())
            .build();
    }
}
