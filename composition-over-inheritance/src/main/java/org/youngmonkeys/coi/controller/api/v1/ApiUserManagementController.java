package org.youngmonkeys.coi.controller.api.v1;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;

import lombok.AllArgsConstructor;

@Api
@Controller("/api/v1")
@AllArgsConstructor
public class ApiUserManagementController {

    private UserService userService;
    private ModelToResponseConverter responseConverter;

    @DoPut("/user-management/latest-user")
    public UserResponse userManagementLatestUserGet() {
        long latestUserId = userService.getLatestUserId();
        return getUserByIdToResponse(latestUserId);
    }

    private UserResponse getUserByIdToResponse(
        long userId
    ) {
        UserModel user = userService.getUserById(userId);
        return responseConverter.toResponse(user);
    }
}
