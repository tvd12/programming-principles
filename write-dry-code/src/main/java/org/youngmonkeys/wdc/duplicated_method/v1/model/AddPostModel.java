package org.youngmonkeys.wdc.duplicated_method.v1.model;

import lombok.Getter;

@Getter
public class AddPostModel {
    private String title;
    private String content;
    private long categoryId;
}
