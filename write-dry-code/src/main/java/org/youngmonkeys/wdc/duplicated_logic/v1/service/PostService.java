package org.youngmonkeys.wdc.duplicated_logic.v1.service;

import org.youngmonkeys.wdc.duplicated_logic.v1.entity.Post;
import org.youngmonkeys.wdc.duplicated_logic.v1.entity.PostStatus;
import org.youngmonkeys.wdc.duplicated_logic.v1.exception.PostNotFoundException;
import org.youngmonkeys.wdc.duplicated_logic.v1.repository.PostRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void publishPost(long postId) {
        Post entity = postRepository.findById(postId);
        if (entity == null) {
            throw new PostNotFoundException(
                "postId: " + postId + " not found"
            );
        }
        entity.setStatus(PostStatus.PUBLISHED);
        postRepository.save(entity);
    }

    public void unpublishPost(long postId) {
        Post entity = postRepository.findById(postId);
        if (entity == null) {
            throw new PostNotFoundException(
                "postId: " + postId + " not found"
            );
        }
        entity.setStatus(PostStatus.DRAFT);
        postRepository.save(entity);
    }
}
