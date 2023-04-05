package org.youngmonkeys.dyc.repo;

import org.youngmonkeys.dyc.entity.Post;
import org.youngmonkeys.dyc.pagination.PostFilter;
import org.youngmonkeys.dyc.pagination.PostPaginationParameter;

import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public class PostPaginationRepository extends PaginationRepository<
    PostFilter,
    PostPaginationParameter,
    Long,
    Post
> {

    @Override
    protected String makeMatchingCondition(PostFilter filter) {
        return filter.matchingCondition();
    }

    @Override
    protected String makePaginationCondition(
        PostPaginationParameter paginationParameter,
        boolean nextPage
    ) {
        return paginationParameter.paginationCondition(nextPage);
    }

    @Override
    protected String makeOrderBy(
        PostPaginationParameter paginationParameter,
        boolean nextPage
    ) {
        return paginationParameter.orderBy(nextPage);
    }

    @Override
    protected Class<Post> getEntityType() {
        return Post.class;
    }
}
