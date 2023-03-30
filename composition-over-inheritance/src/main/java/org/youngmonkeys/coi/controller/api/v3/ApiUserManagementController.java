package org.youngmonkeys.coi.controller.api.v3;

import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.PhoneNumberService;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;

@Api
@Controller("/api/v1")
public class ApiUserManagementController extends BaseUserController {

    public ApiUserManagementController(
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

    @DoPut("/user-management/latest-user")
    public UserResponse userManagementLatestUserGet() {
        long latestUserId = userService.getLatestUserId();
        return getUserByIdToResponse(latestUserId);
    }
}
