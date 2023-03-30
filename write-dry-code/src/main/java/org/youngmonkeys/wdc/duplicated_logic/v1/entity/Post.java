package org.youngmonkeys.wdc.duplicated_logic.v1.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private long id;
    private String title;
    private String content;
    private PostStatus status;
}
