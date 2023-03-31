package org.youngmonkeys.yagni.v1.controller;

import java.util.List;

import org.youngmonkeys.yagni.v1.dto.UserDTO;
import org.youngmonkeys.yagni.v1.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPut;

import lombok.AllArgsConstructor;

@Controller("/api/v1")
@AllArgsConstructor
public class UserController extends BaseController<Long, UserDTO> {

    private final UserService userService;

    @Override
    public List<UserDTO> getDataList(int skip, int limit) {
        throw new UnsupportedOperationException("unsupported");
    }

    @DoGet("/users/{userId}")
    @Override
    public UserDTO getDataById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public Long addData(UserDTO data) {
        throw new UnsupportedOperationException("unsupported");
    }

    @Override
    public void deleteDataById(Long id) {
        throw new UnsupportedOperationException("unsupported");
    }

    @DoPut("/users")
    @Override
    public void updateData(UserDTO data) {
        userService.updateUser(data);
    }
}
