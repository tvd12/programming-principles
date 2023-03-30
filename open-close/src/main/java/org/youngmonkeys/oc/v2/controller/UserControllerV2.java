package org.youngmonkeys.oc.v2.controller;

import static java.util.Collections.singletonMap;

import org.youngmonkeys.oc.v1.model.UserModel;
import org.youngmonkeys.oc.v1.service.UserService;
import org.youngmonkeys.oc.v2.converter.ModelToResponseConverterV2;
import org.youngmonkeys.oc.v2.response.UserResponseV2;

import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;

import lombok.AllArgsConstructor;

@Controller("/api/v2")
@AllArgsConstructor
public class UserControllerV2 {

    private final UserService userService;
    private final ModelToResponseConverterV2 modelToResponseConverterV2;

    @DoGet("/users/{uuid}")
    public UserResponseV2 usersUuidGet(
        @PathVariable String uuid
    ) {
        UserModel model = userService.getUserByUuid(uuid);
        if (model == null) {
            throw new HttpNotFoundException(
                singletonMap("user", "notFound")
            );
        }
        return modelToResponseConverterV2.toResponse(
            model
        );
    }
}
