package org.youngmonkeys.sc.v2.service;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

import java.util.List;

import org.youngmonkeys.sc.v1.converter.EntityToModelConverter;
import org.youngmonkeys.sc.v1.model.PostModel;
import org.youngmonkeys.sc.v1.repo.PostRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaginationPostService {

    private final PostRepository postRepository;
    private final EntityToModelConverter entityToModelConverter;

    public List<PostModel> getPostsOrderByPriorityDescAndIdDesc(
        int priorityInclusive,
        int idExclusive,
        int limit
    ) {
        return newArrayList(
            postRepository.findPosts(
                priorityInclusive,
                idExclusive,
                limit
            ),
            entityToModelConverter::toModel
        );
    }
}
