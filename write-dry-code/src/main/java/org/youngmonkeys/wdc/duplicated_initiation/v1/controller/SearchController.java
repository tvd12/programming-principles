package org.youngmonkeys.wdc.duplicated_initiation.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.youngmonkeys.wdc.duplicated_initiation.v1.response.UserResponse;
import org.youngmonkeys.wdc.duplicated_initiation.v1.service.UserService;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SearchController {

    private final UserService userService;

    @DoGet("/api/v1/search-users")
    public List<UserResponse> searchUsers(
        @RequestParam String keyword
    ) {
        return userService.getUsersByKeyword(
            keyword
        )
            .stream()
            .map(model ->
                UserResponse
                    .builder()
                    .uuid(model.getUuid())
                    .displayName(model.getDisplayName())
                    .profileUrl(model.getProfileUrl())
                    .build()
            )
            .collect(Collectors.toList());
    }
}
