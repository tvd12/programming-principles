package org.youngmonkeys.dyc.service;

import org.youngmonkeys.dyc.converter.EntityToModelConverter;
import org.youngmonkeys.dyc.entity.Post;
import org.youngmonkeys.dyc.model.PostModel;
import org.youngmonkeys.dyc.pagination.PostFilter;
import org.youngmonkeys.dyc.pagination.PostIdDescPaginationParameter;
import org.youngmonkeys.dyc.pagination.PostPaginationParameter;
import org.youngmonkeys.dyc.pagination.PostPaginationSortOrder;
import org.youngmonkeys.dyc.repo.PostPaginationRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class PostPaginationService extends DefaultPaginationService<
    PostModel,
    PostFilter,
    PostPaginationParameter,
    Long,
    Post> {

    private final EntityToModelConverter entityToModelConverter;

    public PostPaginationService(
        PostPaginationRepository repository,
        EntityToModelConverter entityToModelConverter
    ) {
        super(repository);
        this.entityToModelConverter = entityToModelConverter;
    }

    @Override
    protected PostModel convertEntity(Post entity) {
        return entityToModelConverter.toModel(entity);
    }

    @Override
    protected String serializeToPageToken(
        PostPaginationParameter paginationParameter,
        PostModel value
    ) {
        PostPaginationSortOrder sortOrder =
            paginationParameter.sortOrder();
        if (sortOrder == PostPaginationSortOrder.ID_DESC) {
            return sortOrder + ":" + value.getId();
        }
        throw new IllegalArgumentException("unknown sort order");
    }

    @Override
    protected PostPaginationParameter deserializePageToken(
        String pageToken
    ) {
        String[] strs = pageToken.split(":");
        PostPaginationSortOrder sortOrder = PostPaginationSortOrder
            .valueOf(strs[0]);
        if (sortOrder == PostPaginationSortOrder.ID_DESC) {
            return PostIdDescPaginationParameter
                .builder()
                .postId(Long.valueOf(strs[1]))
                .build();
        }
        throw new IllegalArgumentException("unknown sort order");
    }
}
