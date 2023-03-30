package org.youngmonkeys.kiss.reduce_if_else.v2.controller;

import org.youngmonkeys.kiss.reduce_if_else.model.UserModel;
import org.youngmonkeys.kiss.reduce_if_else.model.UserStatus;
import org.youngmonkeys.kiss.reduce_if_else.request.AddPostRequest;
import org.youngmonkeys.kiss.reduce_if_else.service.PostService;
import org.youngmonkeys.kiss.reduce_if_else.service.UserService;
import org.youngmonkeys.kiss.reduce_if_else.v2.validator.PostValidator;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {

    private final UserService userService;
    private final PostService postService;
    private final PostValidator postValidator;

    @DoPost("/api/v1/posts/add")
    public void addPost(
        long userId,
        @RequestBody AddPostRequest request
    ) {
        UserModel user = userService.getUserById(userId);
        UserStatus userStatus = user.getStatus();
        postValidator.validate(
            userStatus,
            request
        );
        if (userStatus == UserStatus.ACTIVATED) {
            postService.addPost(
                userId,
                request.getTitle(),
                request.getContent()
            );
        } else if (userStatus == UserStatus.INACTIVATE) {
            postService.addDraftPost(
                userId,
                request.getTitle(),
                request.getContent()
            );
        }
    }
}
