package org.youngmonkeys.sc.v2.controller;

import java.util.List;

import org.youngmonkeys.sc.v1.model.PostModel;
import org.youngmonkeys.sc.v1.service.PostService;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller("/api/v1")
public class PaginationPostController {

    private final PostService postService;

    @DoGet("/posts")
    public List<PostModel> getPostsOrderByPriorityAndIdDesc(
        @RequestParam("priorityInclusive") int priorityInclusive,
        @RequestParam("idExclusive") int idExclusive,
        @RequestParam("limit") int limit
    ) {
        return postService.getPosts(
            priorityInclusive,
            idExclusive,
            limit
        );
    }
}
