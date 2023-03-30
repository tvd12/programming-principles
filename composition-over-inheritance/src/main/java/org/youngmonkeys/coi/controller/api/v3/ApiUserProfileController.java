package org.youngmonkeys.coi.controller.api.v3;

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

@Api
@Controller("/api/v1")
public class ApiUserProfileController extends BaseUserController {

    public ApiUserProfileController(
        UserService userService,
        PhoneNumberService phoneNumberService,
        ModelToResponseConverter responseConverter
    ) {
        super(
            userService,
            phoneNumberService,
            responseConverter
        );
    }

    @DoPut("/users/{userId}")
    public UserResponse usersUserIdPut(
        @PathVariable long userId,
        @RequestBody UpdateUserRequest request
    ) {
        userService.updateUser(
            userId,
            request.getName()
        );
        return getUserByIdToResponse(
            userId
        );
    }
}
