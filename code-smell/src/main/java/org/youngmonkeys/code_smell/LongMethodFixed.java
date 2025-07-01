package org.youngmonkeys.code_smell;

import lombok.AllArgsConstructor;
import org.youngmonkeys.code_smell.converter.RequestToModelConverter;
import org.youngmonkeys.code_smell.request.AddUserRequest;
import org.youngmonkeys.code_smell.service.UserService;

@AllArgsConstructor
public class LongMethodFixed {

    private final UserService userService;
    private final RequestToModelConverter requestToModelConverter;

    public void saveUser(AddUserRequest request) {
        userService.saveUser(
            requestToModelConverter.toModel(request)
        );
    }
}
