package org.youngmonkeys.coi.controller.api.v1;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.request.UpdateUserRequest;
import org.youngmonkeys.coi.response.UserResponse;
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

    private UserService userService;
    private ModelToResponseConverter responseConverter;

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

    private UserResponse getUserByIdToResponse(
        long userId
    ) {
        UserModel user = userService.getUserById(userId);
        return responseConverter.toResponse(user);
    }
}
