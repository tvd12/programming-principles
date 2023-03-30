package org.youngmonkeys.coi.controller.service;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.PhoneNumberService;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserControllerService {

    private final UserService userService;
    private final PhoneNumberService phoneNumberService;
    private final ModelToResponseConverter responseConverter;

    public UserResponse getUserByIdToResponse(
        long userId
    ) {
        UserModel user = userService.getUserById(userId);
        return responseConverter.toResponse(
            user,
            phoneNumberService.getPhoneNumberByUserId(userId)
        );
    }
}
