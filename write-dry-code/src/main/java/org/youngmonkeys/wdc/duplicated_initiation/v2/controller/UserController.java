package org.youngmonkeys.wdc.duplicated_initiation.v2.controller;

import static java.util.Collections.singletonMap;

import org.youngmonkeys.wdc.duplicated_initiation.v1.model.UserModel;
import org.youngmonkeys.wdc.duplicated_initiation.v1.response.UserResponse;
import org.youngmonkeys.wdc.duplicated_initiation.v1.service.UserService;
import org.youngmonkeys.wdc.duplicated_initiation.v2.converter.ModelToResponseConverter;

import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelToResponseConverter modelToResponseConverter;

    @DoGet("/api/v1/users/{username}")
    public UserResponse getUserByName(
        @PathVariable String username
    ) {
        UserModel model = userService.getUserByName(
            username
        );
        if (model == null) {
            throw new HttpNotFoundException(
                singletonMap("user", "notFound")
            );
        }
        return modelToResponseConverter.toResponse(model);
    }
}