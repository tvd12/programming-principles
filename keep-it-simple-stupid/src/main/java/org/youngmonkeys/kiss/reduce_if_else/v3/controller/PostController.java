package org.youngmonkeys.kiss.reduce_if_else.v3.controller;

import static java.util.Collections.singletonMap;

import java.util.Map;

import org.youngmonkeys.kiss.reduce_if_else.model.UserModel;
import org.youngmonkeys.kiss.reduce_if_else.model.UserStatus;
import org.youngmonkeys.kiss.reduce_if_else.request.AddPostRequest;
import org.youngmonkeys.kiss.reduce_if_else.service.PostService;
import org.youngmonkeys.kiss.reduce_if_else.service.UserService;
import org.youngmonkeys.kiss.reduce_if_else.v3.validator.PostValidator;

import com.tvd12.ezyfox.util.EzyMapBuilder;
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
    private final PostValidator postValidator;

    private final Map<UserStatus, PostAppender>
        postAppenderByUserStatus =
        EzyMapBuilder
            .mapBuilder()
            .put(
                UserStatus.ACTIVATED,
                new PostAppender()
            )
            .put(
                UserStatus.INACTIVATE,
                new PostAppender() {
                    @Override
                    void addPost(
                        long userId,
                        AddPostRequest request
                    ) {
                        postService.addDraftPost(
                            userId,
                            request.getTitle(),
                            request.getContent()
                        );
                    }
                }
            )
            .put(
                UserStatus.BLOCKED,
                new PostAppender() {
                    @Override
                    void validate(AddPostRequest request) {
                        throw new HttpForbiddenException(
                            singletonMap(
                                "userStatus",
                                UserStatus.BLOCKED
                            )
                        );
                    }
                }
            )
            .toMap();

    @DoPost("/api/v1/posts/add")
    public void addPost(
        long userId,
        @RequestBody AddPostRequest request
    ) {
        UserModel user = userService.getUserById(userId);
        UserStatus userStatus = user.getStatus();
        PostAppender postAppender = postAppenderByUserStatus
            .get(userStatus);
        postAppender.validate(request);
        postAppender.addPost(userId, request);
    }

    class PostAppender {

        void addPost(
            long userId,
            AddPostRequest request
        ) {
            postService.addPost(
                userId,
                request.getTitle(),
                request.getContent()
            );
        }

        void validate(AddPostRequest request) {
            postValidator.validate(request);
        }
    }
}
