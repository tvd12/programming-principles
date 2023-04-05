package org.youngmonkeys.dyc.controller.api;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

import org.youngmonkeys.dyc.model.PaginationModel;
import org.youngmonkeys.dyc.model.PostModel;
import org.youngmonkeys.dyc.pagination.PostFilter;
import org.youngmonkeys.dyc.pagination.PostTypeFilter;
import org.youngmonkeys.dyc.service.PostPaginationService;

import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Api
@Controller("/api/v1")
@AllArgsConstructor
public class ApiPostController {

    private final PostPaginationService postPaginationService;

    @DoGet("/posts")
    public PaginationModel<PostModel> postsGet(
        @RequestParam("type") String postType,
        @RequestParam("nextPageToken") String nextPageToken,
        @RequestParam("prevPageToken") String prevPageToken,
        @RequestParam("limit") int limit,
        @RequestParam("lastPage") boolean lastPage
    ) {
        PostFilter filter = PostTypeFilter
            .builder()
            .postType(postType)
            .build();
        if (lastPage) {
            return postPaginationService.getLastPage(filter, limit);
        }
        if (isBlank(prevPageToken)) {
            return postPaginationService.getNextPage(filter, nextPageToken, limit);
        }
        return postPaginationService.getPreviousPage(filter, prevPageToken, limit);
    }
}
