package org.youngmonkeys.oc.v1.controller.api;

import static java.util.Collections.singletonMap;

import org.youngmonkeys.oc.v1.converter.ModelToResponseConverter;
import org.youngmonkeys.oc.v1.model.UserModel;
import org.youngmonkeys.oc.v1.response.UserResponse;
import org.youngmonkeys.oc.v1.service.UserService;

import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;

import lombok.AllArgsConstructor;

@Controller("/api/{apiVersion}")
@AllArgsConstructor
public class UpdatedUserController {

    private final UserService userService;
    private final ModelToResponseConverter modelToResponseConverter;

    @DoGet("/users/{uuid}")
    public UserResponse usersUuidGet(
        @PathVariable("apiVersion") String apiVersion,
        @PathVariable("uuid") String uuid
    ) {
        if (!apiVersion.equals("v1") &&
            !apiVersion.equals("v2")
        ) {
            throw new HttpNotFoundException(
                singletonMap("api", "notFound")
            );
        }
        UserModel model = userService.getUserByUuid(uuid);
        if (model == null) {
            throw new HttpNotFoundException(
                singletonMap("user", "notFound")
            );
        }
        return modelToResponseConverter.toResponse(
            apiVersion,
            model
        );
    }
}
