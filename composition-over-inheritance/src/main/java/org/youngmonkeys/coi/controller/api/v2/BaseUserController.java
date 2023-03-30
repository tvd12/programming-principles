package org.youngmonkeys.coi.controller.api.v2;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseUserController {

    protected UserService userService;
    protected ModelToResponseConverter responseConverter;

    protected UserResponse getUserByIdToResponse(
        long userId
    ) {
        UserModel user = userService.getUserById(userId);
        return responseConverter.toResponse(user);
    }
}
