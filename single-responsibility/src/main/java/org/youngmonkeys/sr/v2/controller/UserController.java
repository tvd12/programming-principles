package org.youngmonkeys.sr.v2.controller;

import org.youngmonkeys.sr.v1.model.User;
import org.youngmonkeys.sr.v2.annotation.Username;
import org.youngmonkeys.sr.v2.request.UpdateAvatarRequest;
import org.youngmonkeys.sr.v2.service.UserService;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Controller("/api/v1")
@AllArgsConstructor
public class UserController extends EzyLoggable {

    private final UserService userService;

    @DoGet("/user/profile")
    public User getUserProfile(
        @Username String username
    ) {
        return userService.getUserByName(username);
    }

@DoPut("/user/avatar")
public ResponseEntity updateUserAvatar(
    @Username String username,
    @RequestBody UpdateAvatarRequest request
    ) {
    userService.updateUserAvatar(
        username,
        request.getNewAvatarId()
    );
    return ResponseEntity.noContent();
}
}
