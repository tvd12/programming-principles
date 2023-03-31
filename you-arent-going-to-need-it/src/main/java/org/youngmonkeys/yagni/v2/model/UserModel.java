package org.youngmonkeys.yagni.v2.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserModel {
    private long userId;
    private String displayName;
    private String avatarUrl;
}
