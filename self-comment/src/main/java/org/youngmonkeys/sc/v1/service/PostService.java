package org.youngmonkeys.sc.v1.service;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

import java.util.List;

import org.youngmonkeys.sc.v1.converter.EntityToModelConverter;
import org.youngmonkeys.sc.v1.model.PostModel;
import org.youngmonkeys.sc.v1.repo.PostRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EntityToModelConverter entityToModelConverter;

    public List<PostModel> getPosts(int priority, int id, int limit) {
        return newArrayList(
            postRepository.findPosts(priority, id, limit),
            entityToModelConverter::toModel
        );
    }
}
