package org.youngmonkeys.kiss.reduce_if_else.v1.controller;

import org.youngmonkeys.kiss.reduce_if_else.model.UserModel;
import org.youngmonkeys.kiss.reduce_if_else.model.UserStatus;
import org.youngmonkeys.kiss.reduce_if_else.request.AddPostRequest;
import org.youngmonkeys.kiss.reduce_if_else.service.PostService;
import org.youngmonkeys.kiss.reduce_if_else.service.UserService;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.exception.HttpForbiddenException;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @DoPost("/api/v1/posts/add")
    public void addPost(
        long userId,
        @RequestBody AddPostRequest request
    ) {
        UserModel user = userService.getUserById(userId);
        UserStatus userStatus = user.getStatus();
        if (userStatus == UserStatus.ACTIVATED) {
            String postTitle = request.getTitle();
            if (postTitle != null && postTitle.length() > 6) {
                postService.addPost(
                    userId,
                    postTitle,
                    request.getContent()
                );
            } else {
                throw new HttpBadRequestException(
                    "Invalid post title"
                );
            }
        } else if (userStatus == UserStatus.INACTIVATE) {
            String postTitle = request.getTitle();
            if (postTitle != null && postTitle.length() > 6) {
                postService.addDraftPost(
                    userId,
                    postTitle,
                    request.getContent()
                );
            } else {
                throw new HttpBadRequestException(
                    "Invalid post title"
                );
            }
        } else {
            throw new HttpForbiddenException(
                "Block user can not add a post"
            );
        }
    }
}
