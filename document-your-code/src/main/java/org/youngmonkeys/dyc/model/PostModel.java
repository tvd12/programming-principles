package org.youngmonkeys.dyc.model;

import org.youngmonkeys.dyc.entity.PostStatus;
import org.youngmonkeys.dyc.entity.PostType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostModel {
    private long id;
    private String title;
    private String content;
    private PostType type;
    private PostStatus status;
}
