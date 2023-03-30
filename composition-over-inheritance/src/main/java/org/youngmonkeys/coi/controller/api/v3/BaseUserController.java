package org.youngmonkeys.coi.controller.api.v3;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.PhoneNumberService;
import org.youngmonkeys.coi.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseUserController {

    protected UserService userService;
    protected PhoneNumberService phoneNumberService;
    protected ModelToResponseConverter responseConverter;

    protected UserResponse getUserByIdToResponse(
        long userId
    ) {
        UserModel user = userService.getUserById(userId);
        return responseConverter.toResponse(
            user,
            phoneNumberService.getPhoneNumberByUserId(userId)
        );
    }
}
