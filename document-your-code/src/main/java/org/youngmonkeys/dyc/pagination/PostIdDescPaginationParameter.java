package org.youngmonkeys.dyc.pagination;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostIdDescPaginationParameter implements PostPaginationParameter {
    private Long postId;

    @Override
    public String paginationCondition(boolean nextPage) {
        if (postId == null) {
            return null;
        }
        return nextPage
               ? "e.id < :postId"
               : "e.id > :postId";
    }

    @Override
    public String orderBy(boolean nextPage) {
        return nextPage
               ? "e.id DESC"
               : "e.id ASC";
    }

    @Override
    public PostPaginationSortOrder sortOrder() {
        return PostPaginationSortOrder.ID_DESC;
    }
}
