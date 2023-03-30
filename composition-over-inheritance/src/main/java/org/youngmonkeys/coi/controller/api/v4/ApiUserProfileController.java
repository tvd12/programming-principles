package org.youngmonkeys.coi.controller.api.v4;

import org.youngmonkeys.coi.controller.api.v3.BaseUserController;
import org.youngmonkeys.coi.controller.service.UserControllerService;
import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.request.UpdateUserRequest;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.PhoneNumberService;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Api
@Controller("/api/v1")
@AllArgsConstructor
public class ApiUserProfileController {

    private final UserService userService;
    private final UserControllerService userControllerService;

    @DoPut("/users/{userId}")
    public UserResponse usersUserIdPut(
        @PathVariable long userId,
        @RequestBody UpdateUserRequest request
    ) {
        userService.updateUser(
            userId,
            request.getName()
        );
        return userControllerService.getUserByIdToResponse(
            userId
        );
    }
}
