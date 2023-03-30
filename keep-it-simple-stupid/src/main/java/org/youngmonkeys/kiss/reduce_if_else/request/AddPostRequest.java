package org.youngmonkeys.kiss.reduce_if_else.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPostRequest {
    private String title;
    private String content;
}
