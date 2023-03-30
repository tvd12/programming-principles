package org.youngmonkeys.kiss.reduce_if_else.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class PostService {

    public void addPost(
        long authorId,
        String title,
        String content
    ) {}

    public void addDraftPost(
        long authorId,
        String title,
        String content
    ) {}
}
