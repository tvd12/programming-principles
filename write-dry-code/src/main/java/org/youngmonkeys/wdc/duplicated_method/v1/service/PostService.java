package org.youngmonkeys.wdc.duplicated_method.v1.service;

import org.youngmonkeys.wdc.duplicated_method.v1.converter.PostModelToEntityConverter;
import org.youngmonkeys.wdc.duplicated_method.v1.entity.Post;
import org.youngmonkeys.wdc.duplicated_method.v1.model.AddPostModel;
import org.youngmonkeys.wdc.duplicated_method.v1.repository.PostRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostModelToEntityConverter postModelToEntityConverter;

    public void addPost(AddPostModel model) {
        postRepository.save(
            postModelToEntityConverter.toEntity(model)
        );
    }
}
