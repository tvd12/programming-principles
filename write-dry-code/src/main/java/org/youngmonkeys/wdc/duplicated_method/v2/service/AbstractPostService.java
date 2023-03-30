package org.youngmonkeys.wdc.duplicated_method.v2.service;

import org.youngmonkeys.wdc.duplicated_method.v1.model.AddPostModel;
import org.youngmonkeys.wdc.duplicated_method.v1.repository.PostRepository;
import org.youngmonkeys.wdc.duplicated_method.v2.converter.ModelToEntityConverter;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AbstractPostService {

    private final PostRepository postRepository;
    private final ModelToEntityConverter modelToEntityConverter;

    public void addPost(AddPostModel model) {
        postRepository.save(
            modelToEntityConverter.toEntity(model)
        );
    }
}
