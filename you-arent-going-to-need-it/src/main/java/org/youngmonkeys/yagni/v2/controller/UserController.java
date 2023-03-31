package org.youngmonkeys.yagni.v2.controller;

import java.util.List;

import org.youngmonkeys.yagni.v1.converter.ModelToResponseConverter;
import org.youngmonkeys.yagni.v1.converter.RequestToModelConverter;
import org.youngmonkeys.yagni.v1.dto.UserDTO;
import org.youngmonkeys.yagni.v2.request.UpdateUserRequest;
import org.youngmonkeys.yagni.v2.response.UserResponse;
import org.youngmonkeys.yagni.v2.service.UserService;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Controller("/api/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RequestToModelConverter requestToModelConverter;
    private final ModelToResponseConverter modelToResponseConverter;

    @DoGet("/users/{userId}")
    public UserResponse usersUserIdGet(
        @PathVariable long userId
    ) {
        return modelToResponseConverter.toResponse(
            userService.getUserById(userId)
        );
    }


    @DoPut("/users/{userId}")
    public ResponseEntity updateData(
        @PathVariable long userId,
        @RequestBody UpdateUserRequest request
    ) {
        userService.updateUser(
            userId,
            requestToModelConverter.toModel(request)
        );
        return ResponseEntity.noContent();
    }
}
