package org.youngmonkeys.coi.controller.api.v2;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;

@Api
@Controller("/api/v1")
public class ApiUserManagementController extends BaseUserController {

    public ApiUserManagementController(
        UserService userService,
        ModelToResponseConverter responseConverter
    ) {
        super(userService, responseConverter);
    }

    @DoGet("/user-management/latest-user")
    public UserResponse userManagementLatestUserGet() {
        long latestUserId = userService.getLatestUserId();
        return getUserByIdToResponse(latestUserId);
    }
}
